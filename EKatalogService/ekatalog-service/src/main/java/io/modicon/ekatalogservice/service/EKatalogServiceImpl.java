package io.modicon.ekatalogservice.service;

import io.modicon.ekatalogservice.api.dto.GetGoodsResponse;
import io.modicon.ekatalogservice.api.dto.GetPricesResponse;
import io.modicon.ekatalogservice.api.dto.ShopDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class EKatalogServiceImpl implements EKatalogService {

    private final HTMLParser htmlParser;

    @Override
    public GetGoodsResponse getGoods(String goodName) {
        return new GetGoodsResponse(htmlParser.parseSearchResult(goodName));
    }

    @Override
    public GetPricesResponse getPrices(String goodUrl) {
        return new GetPricesResponse(htmlParser.parseGoodPricesSell(goodUrl));
    }
}
