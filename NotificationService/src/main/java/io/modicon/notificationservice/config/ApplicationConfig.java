package io.modicon.notificationservice.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableFeignClients(basePackages = "io.modicon.client.feign")
@EnableScheduling
public class ApplicationConfig {
}
