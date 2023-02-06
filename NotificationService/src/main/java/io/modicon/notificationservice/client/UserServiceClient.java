package io.modicon.notificationservice.client;

import io.modicon.notificationservice.config.ApplicationConfig;
import io.modicon.userservice.api.operation.UserOperation;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service",
        value = "user-service",
        configuration = ApplicationConfig.class)
public interface UserServiceClient extends UserOperation {
}
