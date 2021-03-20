package com.example.minibank.app.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@JsonDeserialize
public class BankAccountForm {
    private String currency;
    private String type;

    @Digits(integer = Integer.MAX_VALUE, fraction = 4, message = "Invalid amount.")
    @Min(value = 0, message = "Invalid amount")
    @Max(value = 10_000_000, message = "Invalid amount. Maximum initial deposit amount is 10,000,000")
    private BigDecimal initialDeposit;
}
