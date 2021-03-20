package com.example.minibank.commons.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
@AllArgsConstructor
public class PageRequestParam {
    private int page = 0;
    private int size = 10;

    public PageRequest getPageRequest() {
        if (page < 0 || size < 1) {
            return PageRequest.of(0, 10);
        }

        if (page >= 1) {
            page = page - 1;
        }

        return PageRequest.of(page, size);
    }
}
