package com.example.minibank.app.dto.response;

import com.example.minibank.commons.constant.SourceAccountType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BankAccountResource {
    private String name;
    private String accountNumber;
    private SourceAccountType type;
    private String currency;
    private BigDecimal amount;
    private Date date;
}
