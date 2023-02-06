package io.modicon.notificationservice.client;

import io.modicon.notificationservice.config.ApplicationConfig;
import io.modicon.telegrambot.client.NotificationMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "telegram-operation",
        value = "telegram-service",
        configuration = ApplicationConfig.class)
public interface TelegramServiceClient {

    @PostMapping("/{userId}")
    void sendMessage(@RequestBody NotificationMessage message, @PathVariable String userId);

}
