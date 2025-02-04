package com.example.tobyspring.exrate;

import com.example.tobyspring.api.SimpleApiExecutor;
import com.example.tobyspring.payment.ExRateProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

@Component
public class WebApiExRateProvider implements ExRateProvider {
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return runApiForExRate(url);

//        System.out.println("API ExRate: " + data.rates().get("KRW"));
    }

    private static BigDecimal runApiForExRate(String url) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = new SimpleApiExecutor().execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            return extractExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static BigDecimal extractExRate(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
}
