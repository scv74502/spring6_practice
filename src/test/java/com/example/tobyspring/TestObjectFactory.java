package com.example.tobyspring;

import com.example.tobyspring.exrate.WebApiExRateProvider;
import com.example.tobyspring.payment.ExRateProvider;
import com.example.tobyspring.payment.ExRateProviderStub;
import com.example.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@Configuration
public class TestObjectFactory {
    @Bean
    public PaymentService paymentService(){
        return new PaymentService(exRateProvider());
    }


    @Bean
    public ExRateProvider exRateProvider(){
        return new ExRateProviderStub(valueOf(1_000));
    }


}
