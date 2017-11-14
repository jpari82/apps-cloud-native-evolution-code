package com.example.billing;

import com.example.payments.Gateway;
import com.example.payments.RecurlyGateway;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.amqp.core.Queue;


@SpringBootApplication
@ComponentScan("com.example.billing")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class BillingApplication {

	@Value("${queueName}")
	String queueName;

	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);
	}

	@Bean
	BillingMessageListner receiver(Gateway paymentGateway) {
		return new BillingMessageListner(paymentGateway);
	}

	@Bean
	MessageListenerAdapter listenerAdapter(BillingMessageListner receiver) {
		return new MessageListenerAdapter(receiver, "process");
	}

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	Gateway paymentGateway() {
		return new RecurlyGateway();
	}
}
