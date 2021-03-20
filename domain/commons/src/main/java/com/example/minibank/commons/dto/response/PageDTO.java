package com.example.minibank.commons.dto.response;

import com.example.minibank.commons.dto.response.base.BaseResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(SnakeCaseStrategy.class)
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"data", "totalElements", "currentPage", "totalPages"})
public class PageDTO<T> extends BaseResponse implements Serializable {
    private List<T> data;
    private long totalElements;
    private int currentPage;
    private int totalPages;

    public void setCurrentPage(int currentPage) {
        this.currentPage = totalElements == 0 ? 0 : currentPage + 1;
    }
}
