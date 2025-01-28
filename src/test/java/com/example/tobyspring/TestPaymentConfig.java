package com.example.tobyspring;

import com.example.tobyspring.payment.ExRateProvider;
import com.example.tobyspring.payment.ExRateProviderStub;
import com.example.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;

@Configuration
public class TestPaymentConfig {
    @Bean
    public PaymentService paymentService(){
        return new PaymentService(exRateProvider(), clock());
    }


    @Bean
    public ExRateProvider exRateProvider(){
        return new ExRateProviderStub(valueOf(1_000));
    }

    @Bean
    public Clock clock() {return Clock.fixed(Instant.now(), ZoneId.systemDefault()); }
}
