package com.example.tobyspring.exrate;

import com.example.tobyspring.api.*;
import com.example.tobyspring.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {

        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getForExRate(url);
    }
}
