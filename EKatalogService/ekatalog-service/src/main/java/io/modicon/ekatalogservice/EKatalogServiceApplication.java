package io.modicon.ekatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EKatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EKatalogServiceApplication.class, args);
    }

}
