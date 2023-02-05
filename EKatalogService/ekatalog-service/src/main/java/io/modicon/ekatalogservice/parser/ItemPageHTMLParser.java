package io.modicon.ekatalogservice.parser;

import io.modicon.ekatalogservice.api.dto.GetItemAndShopsResponse;
import io.modicon.ekatalogservice.api.dto.ItemDto;
import io.modicon.ekatalogservice.api.dto.ShopDto;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ItemPageHTMLParser implements EkatalogHTMLParser<GetItemAndShopsResponse> {

    public GetItemAndShopsResponse parse(Document html) {
        List<ShopDto> shops = new ArrayList<>();
        Elements shopsDiv = html.getElementsByClass("shop-108767 priceElem");
        if (shopsDiv.size() > 0) {
            shopsDiv.forEach(shop -> {
                Element priceInfo = shop.getElementsByClass("where-buy-price").get(0)
                        .getElementsByTag("a").get(0);

                String dirtyRef = priceInfo.attr("onmouseover");
                String shopRef = dirtyRef.substring(11, dirtyRef.indexOf(";") - 1);
                String price = priceInfo.text().split(" ")[0];
                shops.add(new ShopDto(SITE_URL + shopRef, BigDecimal.valueOf(Double.parseDouble(price))));
            });
        }
        Element itemLabel = html.getElementsByClass("title-for-page").get(0);
        String itemName = itemLabel.text();
        String itemSubLabel = itemLabel.getElementsByClass("item-conf-name ib nobr").get(0).text();

        String imageRef = html.getElementsByClass("img200 h").get(0)
                .getElementsByTag("img").get(0)
                .attr("src");

        BigDecimal priceFrom = null;
        BigDecimal priceTo = null;
        Elements pricesDiv = html.getElementsByClass("desc-big-price ib");
        if (pricesDiv.size() > 0) {
            Elements prices = pricesDiv.get(0).getElementsByTag("span");
            if (prices.size() > 0) {
                String htmlPriceFrom = prices.get(0).getElementsByTag("span").text();
                priceFrom = BigDecimal
                        .valueOf(Double.parseDouble(htmlPriceFrom));
            }
            if (prices.size() > 1) {
                String htmlPriceTo = prices.get(1).getElementsByTag("span").text();
                priceTo = BigDecimal
                        .valueOf(Double.parseDouble(htmlPriceTo));
            }
        }

        String itemRef = null;
        Elements links = html.getElementsByTag("link");
        for (Element link : links) {
            if (link.attr("rel").equals("canonical")) {
                itemRef = link.attr("href");
            }
        }

        ItemDto item = ItemDto.builder()
                .name(itemSubLabel.length() < 10 ? itemName + " " + itemSubLabel : itemName)
                .itemRef(itemRef)
                .priceFrom(priceFrom)
                .priceTo(priceTo)
                .img(imageRef)
                .build();

        return new GetItemAndShopsResponse(item, shops);
    }

}
