package com.ayush.khalti.controller;

import com.ayush.khalti.pojo.*;
import com.ayush.khalti.service.KhaltiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/khalti")
public class KhaltiController {
    private final KhaltiService khaltiService;

    public KhaltiController(KhaltiService khaltiService) {
        this.khaltiService = khaltiService;
    }


    @PostMapping("/initiate")
    public TokenResponse intiateTranscation(@RequestBody KhaltiRequest khaltiRequest) throws JsonProcessingException {
        return khaltiService.initiateTransaction(khaltiRequest);
    }

    @PostMapping("/confirm")
    public ConfirmSuccessResponse confirm(@RequestBody ConfirmKhaltiRequest khaltiRequest) throws JsonProcessingException {
        return khaltiService.confirmTransaction(khaltiRequest);
    }

    @PostMapping("/verify")
    public VerifyKhaltiResponse verify(@RequestBody VerifyKhaltiRequest khaltiRequest) throws JsonProcessingException {
        return khaltiService.verifyTransaction(khaltiRequest);
    }
}
