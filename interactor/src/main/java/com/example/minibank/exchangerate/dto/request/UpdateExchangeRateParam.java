package com.example.minibank.exchangerate.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateExchangeRateParam {
    private String currency;
    private BigDecimal value;

    public UpdateExchangeRateParam(String currency, BigDecimal value) {
        this.currency = currency.toUpperCase();
        this.value = value;
    }
}
