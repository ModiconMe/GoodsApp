package io.modicon.ekatalogservice.service;

import io.modicon.client.dto.GetItemAndShopsResponse;
import io.modicon.client.dto.GetItemsResponse;
import io.modicon.client.dto.ItemDto;
import io.modicon.ekatalogservice.parser.EKatalogClient;
import io.modicon.ekatalogservice.parser.EkatalogHTMLParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EKatalogServiceImpl implements EKatalogService {

    private final EkatalogHTMLParser<List<ItemDto>> searchResultListHTMLParser;
    private final EkatalogHTMLParser<GetItemAndShopsResponse> itemPageHTMLParser;
    private final EKatalogClient eKatalogClient;

    private static final String SEARCH_URL = "https://n-katalog.ru/search?keyword=";

    public EKatalogServiceImpl(EkatalogHTMLParser<List<ItemDto>> searchResultListHTMLParser,
                               EkatalogHTMLParser<GetItemAndShopsResponse> itemPageHTMLParser,
                               EKatalogClient eKatalogClient
    ) {
        this.searchResultListHTMLParser = searchResultListHTMLParser;
        this.itemPageHTMLParser = itemPageHTMLParser;
        this.eKatalogClient = eKatalogClient;
    }

    @Override
    public GetItemsResponse getItems(String itemName) {
        String searchUrl = SEARCH_URL + itemName;
        return new GetItemsResponse(searchResultListHTMLParser.parse(eKatalogClient.getHTML(searchUrl)));
    }

    @Override
    public GetItemAndShopsResponse getPrices(String itemUrl) {
        return itemPageHTMLParser.parse(eKatalogClient.getHTML(itemUrl));
    }
}
