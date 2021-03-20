package com.example.minibank.domain.model;

import com.example.minibank.commons.constant.TransactionStatus;
import com.example.minibank.commons.constant.TransactionType;
import com.example.minibank.commons.model.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Transaction extends Base {
    private Long id;
    private String referenceNo;
    private String accountNumber;
    private String currency;
    private BigDecimal amount;
    private BigDecimal remainingBalance;
    private TransactionType type;
    private Date date;
    private TransactionStatus transactionStatus;
    private String freeText;
}
