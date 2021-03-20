package com.example.minibank.transactions.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CashOutParam {
    private BigDecimal amount;
}
