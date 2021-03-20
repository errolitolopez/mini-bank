package com.example.minibank.commons.model;

import com.example.minibank.commons.constant.GenericStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Date;


@Data
@JsonPropertyOrder({"status", "createdDate", "modifiedDate"})
public class Base {
    private Date createdDate;
    @JsonIgnore
    private Date modifiedDate;
    private GenericStatus status;
}
