package com.ayush.khalti.service;

import com.ayush.khalti.pojo.*;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface KhaltiService {
     TokenResponse initiateTransaction(KhaltiRequest khaltiRequest) throws JsonProcessingException;
    ConfirmSuccessResponse confirmTransaction(ConfirmKhaltiRequest confirmKhaltiRequest) throws JsonProcessingException;
    VerifyKhaltiResponse  verifyTransaction(VerifyKhaltiRequest verifyKhaltiRequest) throws JsonProcessingException;

}
