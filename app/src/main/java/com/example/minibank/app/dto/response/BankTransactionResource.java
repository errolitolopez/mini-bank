package com.example.minibank.app.dto.response;

import com.example.minibank.commons.constant.TransactionStatus;
import com.example.minibank.commons.constant.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BankTransactionResource {
    private String name;
    private String accountNumber;
    private String referenceNo;
    private String currency;
    private BigDecimal amount;
    private BigDecimal remainingBalance;
    private TransactionType type;
    private Date date;
    @JsonProperty("status")
    private TransactionStatus transactionStatus;
    @JsonProperty("description")
    private String freeText;

    public void setFreeText(String freeText) {
        this.freeText = freeText != null ? freeText : "";
    }



}
