package com.example.minibank.exchangerate;

import com.example.minibank.exchangerate.dto.response.ExchangeRateResponse;

import java.util.List;

public interface GetAllExchangeRates {

    List<ExchangeRateResponse> execute();
}
