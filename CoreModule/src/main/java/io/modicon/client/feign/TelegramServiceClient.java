package io.modicon.client.feign;

import io.modicon.client.dto.NotificationMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "telegram-bot", value = "telegram-bot")
public interface TelegramServiceClient {

    @PostMapping("/telegram/{userId}")
    void sendMessage(@RequestBody NotificationMessage message, @PathVariable String userId);

}
