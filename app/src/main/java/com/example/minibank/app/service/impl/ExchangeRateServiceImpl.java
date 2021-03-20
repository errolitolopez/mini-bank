package com.example.minibank.app.service.impl;

import com.example.minibank.app.dto.response.ExchangeRateResource;
import com.example.minibank.app.dto.response.UpdateExchangeRateResource;
import com.example.minibank.app.service.ExchangeRateService;
import com.example.minibank.commons.dto.response.ServiceResponse;
import com.example.minibank.commons.exception.NotValidException;
import com.example.minibank.data.remoteservice.ExchangeRateClientService;
import com.example.minibank.exchangerate.GetAllExchangeRates;
import com.example.minibank.exchangerate.UpdateExchangeRate;
import com.example.minibank.exchangerate.dto.request.UpdateExchangeRateParam;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ModelMapper mapper = new ModelMapper();

    private final ExchangeRateClientService exchangeRateClientService;

    private final UpdateExchangeRate updateExchangeRate;
    private final GetAllExchangeRates getAllExchangeRates;

    @SneakyThrows
    @Override
    public ServiceResponse<UpdateExchangeRateResource> updateExchangeRate() {
        Map<String, Object> params = new HashMap<>();
        params.put("base", "PHP");

        Object exchangeRate;

        try {

            exchangeRate = exchangeRateClientService.getExchangeRate(params);

        } catch (RetryableException e) {

            throw new NotValidException("Unable to connect to real-time exchange rate API. " +
                    "Please try again later.");
        }

        String JSONString = objectMapper.writeValueAsString(exchangeRate);

        ObjectNode node = new ObjectMapper().readValue(JSONString, ObjectNode.class);

        if (node.has("rates")) {

            final String rates = node.get("rates").toPrettyString();

            Map<String, BigDecimal> map = objectMapper
                    .readValue(rates, new TypeReference<HashMap<String, BigDecimal>>() {
                    });

            Set<UpdateExchangeRateParam> updateExchangeRateParam = new HashSet<>();

            map.forEach((key, value) -> {
                updateExchangeRateParam.add(new UpdateExchangeRateParam(key, value));
            });

            final List<ExchangeRateResource> exchangeRateResources = updateExchangeRate.execute(updateExchangeRateParam)
                    .stream()
                    .map(o -> mapper.map(o, ExchangeRateResource.class))
                    .collect(Collectors.toList());

            UpdateExchangeRateResource updateExchangeRateResource = new UpdateExchangeRateResource();
            updateExchangeRateResource.setRates(exchangeRateResources);

            final String date = node.get("date").asText();

            updateExchangeRateResource.setSyncDate(date);

            ServiceResponse<UpdateExchangeRateResource> serviceResponse = new ServiceResponse<>();
            serviceResponse.setData(updateExchangeRateResource);
            serviceResponse.setMessage("Transaction successful. Exchange rates successfully updated.");
            return serviceResponse;

        } else {
            throw new NotValidException("Transaction failed. " +
                    "Real-time exchange rate is outdated and can't read values.");
        }

    }

    @Override
    public ServiceResponse<List<ExchangeRateResource>> getAllExchangeRates() {

        final List<ExchangeRateResource> exchangeRateResources = getAllExchangeRates.execute()
                .stream()
                .map(o -> mapper.map(o, ExchangeRateResource.class))
                .collect(Collectors.toList());

        ServiceResponse<List<ExchangeRateResource>> serviceResponse = new ServiceResponse<>();
        serviceResponse.setData(exchangeRateResources);
        serviceResponse.setMessage("Transaction successful.");
        return serviceResponse;
    }
}
