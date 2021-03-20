package com.example.minibank.app.listener;


import com.example.minibank.app.listener.task.UpdateExchangeRateTask;
import com.example.minibank.app.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Timer;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;
import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UpdateExchangeRateEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private final ExchangeRateService exchangeRateService;

    /**
     * Update exchange rate every 12:00:00 AM
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // Calling updateExchangeRate to update at first time
         exchangeRateService.updateExchangeRate();

        // Set tomorrow date
        Calendar date = getInstance();
        date.set(date.get(YEAR), date.get(MONTH), date.get(DATE) + 1, 0, 0, 0);

        // Set interval
        final long interval = MILLISECONDS.convert(1, DAYS);

        Timer timer = new Timer();
        timer.schedule(new UpdateExchangeRateTask(exchangeRateService), date.getTime(), interval);

        log.info("Update exchange rate task started.");
    }
}
