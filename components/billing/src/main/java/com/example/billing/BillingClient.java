package com.example.billing;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(fallbackMethod = "reliableBillUser")
    public void billUser (String userId, int billAmount){


        System.out.println("In Billing Client  : Just before calling : " + "//billing/reoccurringPayment");
        logger.info("In Billing Client  : Just before calling : " + "//billing/reoccurringPayment");

        restTemplate.postForEntity("//billing/reocurringPayment", billAmount, String.class);



    }

    public void reliableBillUser(String userId, int billAmount){
        logger.info("From reliable for User : "+ userId + " of amount :" + billAmount);
    }
}
