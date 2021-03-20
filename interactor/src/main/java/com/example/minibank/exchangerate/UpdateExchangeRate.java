package com.example.minibank.exchangerate;

import com.example.minibank.exchangerate.dto.request.UpdateExchangeRateParam;
import com.example.minibank.exchangerate.dto.response.ExchangeRateResponse;

import java.util.List;
import java.util.Set;

public interface UpdateExchangeRate {

    List<ExchangeRateResponse> execute(Set<UpdateExchangeRateParam> updateExchangeRateParams);
}
