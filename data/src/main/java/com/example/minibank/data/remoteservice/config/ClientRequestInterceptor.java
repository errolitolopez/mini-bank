package com.example.minibank.data.remoteservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class ClientRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("Accept", "application/json");
        template.header("Content-Type", "application/json");
    }
}
