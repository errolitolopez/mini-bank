package com.example.minibank.app.dto.request;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CashInForm {

    @NotNull(message = "Invalid amount.")
    @DecimalMin(value = "0.0000", inclusive = false, message = "Invalid amount.")
    @Digits(integer = Integer.MAX_VALUE, fraction = 4, message = "Invalid amount.")
    @Min(value = 1, message = "Invalid amount. Minimum deposit amount is 1")
    @Max(value = 10_000_000, message = "Invalid amount. Maximum deposit amount is 10,000,000")
    private BigDecimal amount;

    @NotNull(message = "Currency is required.")
    private String currency;
}
