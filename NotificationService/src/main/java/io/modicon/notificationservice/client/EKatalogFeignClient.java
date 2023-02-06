package io.modicon.notificationservice.client;

import io.modicon.ekatalogservice.api.operation.EKatalogOperation;
import io.modicon.notificationservice.config.ApplicationConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ekatalog-service",
        url = "${api.eKatalogService-url}",
        configuration = ApplicationConfig.class)
public interface EKatalogFeignClient extends EKatalogOperation {
}
