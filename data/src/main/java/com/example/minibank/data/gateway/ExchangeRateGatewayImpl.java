package com.example.minibank.data.gateway;

import com.example.minibank.data.persistence.entity.ExchangeRateEntity;
import com.example.minibank.data.persistence.repository.ExchangeRateRepository;
import com.example.minibank.data.stereotype.Gateway;
import com.example.minibank.domain.ExchangeRate;
import com.example.minibank.domain.ExchangeRateGateway;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Gateway
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ExchangeRateGatewayImpl implements ExchangeRateGateway {

    private final ExchangeRateRepository exchangeRateRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<ExchangeRate> getAllExchangeRates() {
        return exchangeRateRepository.findAll()
                .stream()
                .map(o -> mapper.map(o, ExchangeRate.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExchangeRate> saveAllExchangeRates(List<ExchangeRate> exchangeRates) {
        final List<ExchangeRateEntity> exchangeRateEntities = exchangeRates.stream()
                .map(o -> mapper.map(o, ExchangeRateEntity.class))
                .collect(Collectors.toList());

        return exchangeRateRepository.saveAll(exchangeRateEntities)
                .stream()
                .map(o -> mapper.map(o, ExchangeRate.class))
                .collect(Collectors.toList());
    }
}
