package com.example.minibank.app.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRateResource {
    private String currency;
    private BigDecimal value;
}
