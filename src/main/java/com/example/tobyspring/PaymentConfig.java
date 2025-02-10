package com.example.tobyspring;

import com.example.tobyspring.api.ApiTemplate;
import com.example.tobyspring.api.ErApiExtractor;
import com.example.tobyspring.api.SimpleApiExecutor;
import com.example.tobyspring.exrate.RestTemplateExRateProvider;
import com.example.tobyspring.payment.ExRateProvider;
import com.example.tobyspring.exrate.WebApiExRateProvider;
import com.example.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentService paymentService(){
        return new PaymentService(exRateProvider(), clock());
    }

//    @Bean
//    public ExRateProvider cachedExRateProvider(){
//        return new CachedExRateProvider(exRateProvider());
//    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(new JdkClientHttpRequestFactory());
    }

    @Bean
    public ApiTemplate apiTemplate(){
        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExtractor());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public Clock clock() {return Clock.systemDefaultZone(); }

}
