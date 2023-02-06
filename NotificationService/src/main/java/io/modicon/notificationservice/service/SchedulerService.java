package io.modicon.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SchedulerService {

    private final PriceCheckService priceCheckService;

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void updatePrices() {
        priceCheckService.checkAndSend();
    }

}
