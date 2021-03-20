package com.example.minibank.exchangerate.impl;

import com.example.minibank.commons.stereotype.Interactor;
import com.example.minibank.domain.ExchangeRate;
import com.example.minibank.domain.ExchangeRateGateway;
import com.example.minibank.exchangerate.UpdateExchangeRate;
import com.example.minibank.exchangerate.dto.request.UpdateExchangeRateParam;
import com.example.minibank.exchangerate.dto.response.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Interactor
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UpdateExchangeRateImpl implements UpdateExchangeRate {

    private final ExchangeRateGateway exchangeRateGateway;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<ExchangeRateResponse> execute(Set<UpdateExchangeRateParam> updateExchangeRateParams) {

        final List<ExchangeRate> exchangeRates = updateExchangeRateParams
                .stream()
                .map(param -> {

                    ExchangeRate exchangeRate = new ExchangeRate();

                    final Optional<ExchangeRate> exchangeRateOptional = exchangeRateGateway.getAllExchangeRates()
                            .stream()
                            .filter(o -> o.getCurrency().equalsIgnoreCase(param.getCurrency()))
                            .findFirst();

                    if (exchangeRateOptional.isPresent()) {
                        exchangeRate = exchangeRateOptional.get();
                    }

                    exchangeRate.setCurrency(param.getCurrency());
                    exchangeRate.setValue(param.getValue());

                    return exchangeRate;

                }).collect(Collectors.toList());


        return exchangeRateGateway.saveAllExchangeRates(exchangeRates)
                .stream()
                .map(o -> mapper.map(o, ExchangeRateResponse.class))
                .collect(Collectors.toList());
    }
}
