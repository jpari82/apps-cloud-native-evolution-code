package com.example.billing;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class BillingClient {

    private String billingService;

    public BillingClient(String billingService){
        this.billingService = billingService;
    }

    public void billUser (String userId, int billAmount){

        RestTemplate restTemplate = new RestTemplate();
        System.out.println("In Billing Client  : Just before calling : " + billingService+"/reoccurringPayment");
        restTemplate.postForEntity(billingService + "/reocurringPayment", billAmount, String.class);


    }
}
