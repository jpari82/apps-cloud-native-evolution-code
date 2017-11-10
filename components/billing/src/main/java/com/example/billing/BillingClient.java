package com.example.billing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class BillingClient {

    private static final Logger logger = LoggerFactory.getLogger(BillingClient.class);

    private RestTemplate restTemplate;

    public BillingClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public void billUser (String userId, int billAmount){


        System.out.println("In Billing Client  : Just before calling : " + "//billing/reoccurringPayment");
        logger.info("In Billing Client  : Just before calling : " + "//billing/reoccurringPayment");
        restTemplate.postForEntity("//billing/reocurringPayment", billAmount, String.class);


    }
}
