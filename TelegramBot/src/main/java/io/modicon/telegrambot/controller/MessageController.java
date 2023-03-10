package io.modicon.telegrambot.controller;

import io.modicon.client.dto.NotificationMessage;
import io.modicon.telegrambot.bot.Bot;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MessageController {

    private final Bot bot;

    @PostMapping("/telegram/{userId}")
    public void sendMessage(@RequestBody NotificationMessage message, @PathVariable String userId) {
        bot.executeMessage(userId, message.text());
    }

}
