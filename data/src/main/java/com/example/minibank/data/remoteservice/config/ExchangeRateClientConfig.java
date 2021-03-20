package com.example.minibank.data.remoteservice.config;

import com.example.minibank.data.remoteservice.ExchangeRateClientService;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeRateClientConfig {

    @Value("${exchange-rate.url}")
    private String url;

    @Bean
    public ExchangeRateClientService exchangeRateClientService() {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .requestInterceptor(new ClientRequestInterceptor())
                .retryer(new CustomRetryer())
                .target(ExchangeRateClientService.class, url);
    }
}
