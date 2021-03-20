package com.example.minibank.domain;

import java.util.List;

public interface ExchangeRateGateway {

    List<ExchangeRate> getAllExchangeRates();

    List<ExchangeRate> saveAllExchangeRates(List<ExchangeRate> exchangeRates);
}
