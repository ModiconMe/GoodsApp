package io.modicon.ekatalogservice.infrastructure.controller;

import io.modicon.ekatalogservice.api.dto.GetItemsResponse;
import io.modicon.ekatalogservice.api.dto.GetItemAndShopsResponse;
import io.modicon.ekatalogservice.api.dto.UrlDto;
import io.modicon.ekatalogservice.api.operation.EKatalogOperation;
import io.modicon.ekatalogservice.service.EKatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EKatalogController implements EKatalogOperation {

    private final EKatalogService eKatalogService;

    @Override
    public GetItemsResponse getItems(String itemName) {
        return eKatalogService.getItems(itemName);
    }

    @Override
    public GetItemAndShopsResponse getItemsWithPrices(UrlDto itemUrl) {
        return eKatalogService.getPrices(itemUrl.url());
    }

}