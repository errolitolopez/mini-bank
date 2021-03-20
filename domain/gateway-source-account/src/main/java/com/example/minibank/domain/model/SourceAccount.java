package com.example.minibank.domain.model;

import com.example.minibank.commons.constant.SourceAccountType;
import com.example.minibank.commons.model.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class SourceAccount extends Base {
    private Long id;
    private String userReferenceNo;
    private String accountNumber;
    private SourceAccountType type;
    private BigDecimal amount;
    private String currency;
}
