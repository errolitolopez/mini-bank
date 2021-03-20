package com.example.minibank.domain.model;

import com.example.minibank.commons.model.Base;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonNaming(SnakeCaseStrategy.class)
public class User extends Base {
    private Long id;
    private String referenceNo;
    private String name;
    private String email;
}
