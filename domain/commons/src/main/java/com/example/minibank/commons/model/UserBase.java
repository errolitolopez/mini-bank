package com.example.minibank.commons.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id", "name", "email", "referenceNo"})
public class UserBase extends Base {
    private Long id;
    private String referenceNo;
    private String name;
    private String email;
}
