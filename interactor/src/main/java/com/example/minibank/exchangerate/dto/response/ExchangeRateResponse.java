package com.example.minibank.exchangerate.dto.response;

import com.example.minibank.commons.dto.response.base.BaseResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ExchangeRateResponse extends BaseResponse {
    private Long id;
    private String currency;
    private BigDecimal value;
    private String description;
}
