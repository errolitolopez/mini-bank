package com.example.minibank.domain;

import com.example.minibank.commons.model.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExchangeRate extends Base {
    private Long id;
    private String currency;
    private BigDecimal value;
    private String description;
}
