package com.example.minibank.app.listener.task;

import com.example.minibank.app.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.TimerTask;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UpdateExchangeRateTask extends TimerTask {

    private final ExchangeRateService exchangeRateService;

    @Override
    public void run() {
        log.info("Update exchange rate task called. {}", new Date());
        exchangeRateService.updateExchangeRate();
    }
}
