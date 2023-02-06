package io.modicon.userservice.client;

import io.modicon.ekatalogservice.api.operation.EKatalogOperation;
import io.modicon.userservice.config.ApplicationConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ekatalog-service", configuration = ApplicationConfig.class)
public interface EKatalogClient extends EKatalogOperation {
}
