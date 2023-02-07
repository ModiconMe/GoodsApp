package io.modicon.client.feign;

import io.modicon.client.operation.UserOperation;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service", value = "user-service")
public interface UserServiceClient extends UserOperation {
}
