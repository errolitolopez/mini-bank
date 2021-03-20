package com.example.minibank.bankaccount.dto.response;

import com.example.minibank.commons.constant.SourceAccountType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BankAccountResponse {
    private SourceAccountType type;
    private String accountNumber;
    private String currency;
    private BigDecimal amount;
}
