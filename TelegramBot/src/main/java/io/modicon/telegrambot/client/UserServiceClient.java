package io.modicon.telegrambot.client;

import io.modicon.telegrambot.config.ApplicationConfig;
import io.modicon.userservice.api.operation.UserOperation;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-operations",
        url = "${api.userService-url}",
        configuration = ApplicationConfig.class)
public interface UserServiceClient extends UserOperation {
}
