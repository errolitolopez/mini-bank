package com.example.minibank.data.remoteservice;

import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

public interface ExchangeRateClientService {

    @RequestLine("GET /latest")
    Object getExchangeRate(@QueryMap Map<String, Object> params);
}
