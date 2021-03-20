package com.example.minibank.app.controller;

import com.example.minibank.app.dto.response.ExchangeRateResource;
import com.example.minibank.app.dto.response.UpdateExchangeRateResource;
import com.example.minibank.app.service.ExchangeRateService;
import com.example.minibank.commons.dto.response.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exchange-rate")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @PutMapping
    public ServiceResponse<UpdateExchangeRateResource> updateExchangeRate() {
        return exchangeRateService.updateExchangeRate();
    }

    @GetMapping
    public ServiceResponse<List<ExchangeRateResource>> getAllExchangeRates() {
        return exchangeRateService.getAllExchangeRates();
    }
}
