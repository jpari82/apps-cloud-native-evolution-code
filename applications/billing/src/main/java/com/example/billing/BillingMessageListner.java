package com.example.billing;

import com.example.payments.Gateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;

public class BillingMessageListner {

    public static final Logger logger = LoggerFactory.getLogger(BillingMessageListner.class);
    @Autowired
    private Gateway paymentGateway;

    public BillingMessageListner(Gateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }


    @RabbitListener(queues={ "#{'${queueName}'}"})
//    @RabbitListener(queues = "billing-queue")
    public void process(@Payload BillingMessage message) {
        System.out.print("Received mesaaaaaaa"  + message.getUserId());
        logger.info("Received <" + message.getUserId()+ ">");
        int amount = message.getAmount();

        if (paymentGateway.createReocurringPayment(amount)){
            logger.info("Successfully created payment for <" + amount + ">");
        } else {
            logger.info("Creating payment for <" + amount + "> failed.");
         }


    }

}
