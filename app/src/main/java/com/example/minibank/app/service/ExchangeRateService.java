package com.example.minibank.app.service;

import com.example.minibank.app.dto.response.ExchangeRateResource;
import com.example.minibank.app.dto.response.UpdateExchangeRateResource;
import com.example.minibank.commons.dto.response.ServiceResponse;

import java.util.List;

public interface ExchangeRateService {

    ServiceResponse<UpdateExchangeRateResource> updateExchangeRate();

    ServiceResponse<List<ExchangeRateResource>> getAllExchangeRates();
}
