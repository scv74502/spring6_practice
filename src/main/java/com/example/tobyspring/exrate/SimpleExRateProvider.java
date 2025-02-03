package com.example.tobyspring.exrate;

import com.example.tobyspring.payment.ExRateProvider;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SimpleExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(String currency) {
        if(currency.equals("USD")) return BigDecimal.valueOf(1000);
        throw new IllegalArgumentException("지원되지 않는 통화입니다");
    }
}
