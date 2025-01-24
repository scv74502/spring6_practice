package com.example.tobyspring;

import com.example.tobyspring.exrate.CachedExRateProvider;
import com.example.tobyspring.payment.ExRateProvider;
import com.example.tobyspring.exrate.WebApiExRateProvider;
import com.example.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {
    @Bean
    public PaymentService paymentService(){
        return new PaymentService(exRateProvider());
    }

//    @Bean
//    public ExRateProvider cachedExRateProvider(){
//        return new CachedExRateProvider(exRateProvider());
//    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new WebApiExRateProvider();
    }


}
