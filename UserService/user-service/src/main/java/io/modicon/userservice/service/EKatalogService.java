package io.modicon.userservice.service;

import feign.FeignException;
import io.modicon.ekatalogservice.api.dto.GetItemAndShopsResponse;
import io.modicon.ekatalogservice.api.dto.UrlDto;
import io.modicon.userservice.client.EKatalogClient;
import io.modicon.userservice.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EKatalogService {

    private final EKatalogClient eKatalogClient;

    public GetItemAndShopsResponse getItemsFromEKatalog(String itemUrl) {
        try {
            log.info("go to the ekatalog service {}", itemUrl);
            var itemsWithPrices = eKatalogClient.getItemsWithPrices(new UrlDto(itemUrl));
            log.info("successfully got from ekatalog service {}", itemsWithPrices);
            return itemsWithPrices;
        } catch (FeignException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw ApiException.exception(HttpStatus.NOT_FOUND, "item with url %s not found", itemUrl);
        }
    }

}
