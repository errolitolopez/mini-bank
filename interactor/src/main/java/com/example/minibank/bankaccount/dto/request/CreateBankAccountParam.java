package com.example.minibank.bankaccount.dto.request;

import com.example.minibank.commons.constant.SourceAccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateBankAccountParam {
    private String currency;
    private SourceAccountType type;
    private BigDecimal initialDeposit;

    public void setInitialDeposit(BigDecimal initialDeposit) {
        this.initialDeposit = initialDeposit != null ? initialDeposit : new BigDecimal(0);
    }
}
