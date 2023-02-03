package io.modicon.ekatalogservice.controller;

import io.modicon.ekatalogservice.api.dto.GetGoodsResponse;
import io.modicon.ekatalogservice.api.operation.EKatalogOperation;
import io.modicon.ekatalogservice.service.EKatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/goods")
public class EKatalogController implements EKatalogOperation {

    private final EKatalogService eKatalogService;

    @Override
    public GetGoodsResponse getGoods(String goodName) {
        return eKatalogService.getGoods(goodName);
    }
}
