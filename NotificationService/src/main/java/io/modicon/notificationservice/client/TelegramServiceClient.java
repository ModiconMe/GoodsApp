package io.modicon.notificationservice.client;

import io.modicon.notificationservice.config.ApplicationConfig;
import io.modicon.telegrambot.client.NotificationMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "telegram-bot",
        value = "telegram-bot",
        configuration = ApplicationConfig.class)
public interface TelegramServiceClient {

    @PostMapping("/telegram/{userId}")
    void sendMessage(@RequestBody NotificationMessage message, @PathVariable String userId);

}
