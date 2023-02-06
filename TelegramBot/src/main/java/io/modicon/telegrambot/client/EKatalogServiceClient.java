package io.modicon.telegrambot.client;

import io.modicon.ekatalogservice.api.operation.EKatalogOperation;
import io.modicon.telegrambot.config.ApplicationConfig;
import io.modicon.userservice.api.operation.UserOperation;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ekatalog-service", value = "ekatalog-service", configuration = ApplicationConfig.class)
public interface EKatalogServiceClient extends EKatalogOperation {
}
