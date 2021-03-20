package com.example.minibank.data.remoteservice.model.response;

import lombok.Data;

@Data
public class ExchangeRateEnvelope {
    private Rates rates;
    private String base;
    private String date;
}
