package com.example.minibank.exchangerate.impl;

import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.ExchangeRateGateway;
import com.example.minibank.exchangerate.GetAllExchangeRates;
import com.example.minibank.exchangerate.dto.response.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GetAllExchangeRatesImpl implements GetAllExchangeRates {

    private final ExchangeRateGateway exchangeRateGateway;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<ExchangeRateResponse> execute() {
        return exchangeRateGateway.getAllExchangeRates()
                .stream()
                .map(o -> mapper.map(o, ExchangeRateResponse.class))
                .collect(Collectors.toList());
    }
}
