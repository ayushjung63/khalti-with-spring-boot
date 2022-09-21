package com.ayush.khalti.service.impl;

import com.ayush.khalti.pojo.*;
import com.ayush.khalti.repo.VerifyKhaltiResponseRepo;
import com.ayush.khalti.service.KhaltiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KhaltiServiceImpl implements KhaltiService {
    private static final String INITIATE_URL="https://khalti.com/api/v2/payment/initiate/";
    private static final String CONFIRM_URL="https://khalti.com/api/v2/payment/confirm/";
    private static final String VERIFY_URL="https://khalti.com/api/v2/payment/verify/";
    private static final String TEST_SECRET_KEY="your_khalti_secret_key";
    private static final String TEST_PUBLIC_KEY="your_khalti_public_key";

    @Autowired
    private RestTemplate restTemplate;

    private final VerifyKhaltiResponseRepo verifyKhaltiResponseRepo;

    @Autowired
    private ObjectMapper objectMapper;

    public KhaltiServiceImpl( VerifyKhaltiResponseRepo verifyKhaltiResponseRepo) {
        this.verifyKhaltiResponseRepo = verifyKhaltiResponseRepo;
    }

    @Override
    public TokenResponse initiateTransaction(KhaltiRequest khaltiRequest) throws JsonProcessingException {
        khaltiRequest.setPublic_key(TEST_PUBLIC_KEY);
        Object response = restTemplate.postForObject(INITIATE_URL, khaltiRequest, Object.class);
        String jsonResponse = objectMapper.writeValueAsString(response);
        TokenResponse tokenResponse = objectMapper.readValue(jsonResponse, TokenResponse.class);
        return tokenResponse;
    }

    @Override
    public ConfirmSuccessResponse confirmTransaction(ConfirmKhaltiRequest confirmKhaltiRequest) throws JsonProcessingException {
        confirmKhaltiRequest.setPublic_key(TEST_PUBLIC_KEY);
        Object response = restTemplate.postForObject(CONFIRM_URL, confirmKhaltiRequest, Object.class);
        String jsonResponse = objectMapper.writeValueAsString(response);
        ConfirmSuccessResponse confirmSuccessResponse = objectMapper.readValue(jsonResponse, ConfirmSuccessResponse.class);
        return confirmSuccessResponse;
    }

    @Override
    public VerifyKhaltiResponse verifyTransaction(VerifyKhaltiRequest verifyKhaltiRequest) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Key "+TEST_SECRET_KEY);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("token", verifyKhaltiRequest.getToken());
        map.add("amount", verifyKhaltiRequest.getAmount().toString());

        HttpEntity<Object> requestEntity =
                new HttpEntity<>(map, headers);

        Object response = restTemplate.exchange(VERIFY_URL,HttpMethod.POST, requestEntity, Object.class);
        String jsonResponse = objectMapper.writeValueAsString(response);
        System.out.println(jsonResponse);

        JSONObject jsonObject=new JSONObject(jsonResponse);
        JSONObject body = (JSONObject) jsonObject.get("body");

        String idx = (String) body.get("idx");
        String productIdentity = (String) body.get("product_identity");
        Integer amount = (Integer) body.get("amount");

        JSONObject type = (JSONObject) body.get("type");
        String typeName = (String) type.get("name");

        JSONObject user = (JSONObject) body.get("user");
        String name = (String) user.get("name");

        VerifyKhaltiResponse verifyKhaltiResponse = VerifyKhaltiResponse.builder()
                .idx(idx)
                .amount(amount)
                .type(typeName)
                .paidBy(name)
                .product_identity(productIdentity)
                .build();

        verifyKhaltiResponseRepo.save(verifyKhaltiResponse);
        return verifyKhaltiResponse;


    }
}
