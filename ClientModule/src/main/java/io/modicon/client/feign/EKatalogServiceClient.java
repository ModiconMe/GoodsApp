package io.modicon.client.feign;

import io.modicon.client.operation.EKatalogOperation;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ekatalog-service", value = "ekatalog-service")
public interface EKatalogServiceClient extends EKatalogOperation {
}
