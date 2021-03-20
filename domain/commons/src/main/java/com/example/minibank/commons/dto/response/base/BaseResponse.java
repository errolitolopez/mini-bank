package com.example.minibank.commons.dto.response.base;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseResponse {
    private String httpStatus = "OK";
    private String message = "Transaction successful.";
    private final ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.of("+08:00"));
}
