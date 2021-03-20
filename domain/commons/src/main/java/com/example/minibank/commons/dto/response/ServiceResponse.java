package com.example.minibank.commons.dto.response;

import com.example.minibank.commons.dto.response.base.BaseResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"data"})
public class ServiceResponse<T> extends BaseResponse {
    private T data;
}
