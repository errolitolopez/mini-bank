package com.example.minibank.transactions.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashInParam {
    private boolean isConverted;
    private BigDecimal amount;
    private BigDecimal total;
    private String currency;
}
