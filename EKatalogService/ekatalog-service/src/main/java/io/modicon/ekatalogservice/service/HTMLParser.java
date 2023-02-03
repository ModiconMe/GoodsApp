package io.modicon.ekatalogservice.service;

import io.modicon.ekatalogservice.api.dto.GoodDto;
import io.modicon.ekatalogservice.api.dto.ShopDto;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class HTMLParser {

    private static final String SEARCH_URL = "https://n-katalog.ru/search?keyword=";
    private static final String SITE_URL = "https://n-katalog.ru";
    private static final String MODEL_SHORT_DIV = "model-short-div list-item--goods";
    private static final String MODEL_SHORT_TITLE = "model-short-title no-u";

    public List<GoodDto> parseSearchResult(String goodName) {
        ArrayList<GoodDto> goods = new ArrayList<>();
        try {
            Document document = Jsoup.connect(SEARCH_URL + goodName).get();
            Elements foundPositions = document.getElementsByClass(MODEL_SHORT_DIV);

            // GoodDto assemble
            foundPositions.forEach(p -> {
                // get goodName
                Elements positionLabel = p.getElementsByClass(MODEL_SHORT_TITLE);
                String positionLabelText = positionLabel.get(0).getElementsByClass("u").text();
                String positionLabelSubtext = positionLabel.get(0).getElementsByClass("list-conf-name ib nobr").text();

                // get ref
                String positionRef = SITE_URL + positionLabel.attr("href");

                // get image
                String imageRef = p
                        .getElementsByClass("list-img h")
                        .get(0).getElementsByTag("img")
                        .get(0).attr("src");

                // get price range
                Elements prices = p.getElementsByClass("model-hot-prices-td")
                        .get(0).getElementsByClass("model-price-range");
                BigDecimal priceFrom = null;
                BigDecimal priceTo = null;
                if (prices.size() > 0) {
                    String combined = prices.get(0).getElementsByTag("span").text();
                    String[] splitPrices = combined.split(" ");

                    if (splitPrices.length == 2) {
                        priceFrom = BigDecimal
                                .valueOf(Double.parseDouble(splitPrices[0] != null ? splitPrices[0] : "0"));
                        priceTo = BigDecimal
                                .valueOf(Double.parseDouble(splitPrices[1] != null ? splitPrices[1] : "0"));
                    } else if (splitPrices.length == 1) {
                        priceFrom = (splitPrices[0] != null) && !splitPrices[0].equals("0") ? BigDecimal.valueOf(Double.parseDouble(splitPrices[0])) : null;
                    }
                }

                GoodDto good = GoodDto.builder()
                        .name(positionLabelSubtext.length() < 10 ? positionLabelText + " " + positionLabelSubtext : positionLabelText)
                        .ref(positionRef)
                        .priceFrom(priceFrom)
                        .priceTo(priceTo)
                        .img(imageRef)
                        .build();
                goods.add(good);
            });
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return goods;
    }

    public List<ShopDto> parseGoodPricesSell(String goodUrl) {
        ArrayList<ShopDto> shops = new ArrayList<>();
        try {
            Document document = Jsoup.connect(goodUrl).get();
            Elements shopsDiv = document.getElementsByClass("shop-108767 priceElem");
            if (shopsDiv.size() > 0) {
                shopsDiv.forEach(shop -> {
                    Element priceInfo = shop.getElementsByClass("where-buy-price").get(0)
                            .getElementsByTag("a").get(0);
//                    System.out.println(priceInfo);
//                    String dirtyRef = priceInfo.attr("onmouseover");
//                    System.out.println(dirtyRef.substring(11, dirtyRef.indexOf(";") - 1));
                    System.out.println(priceInfo.getElementsByClass("hide-blacked"));
//                    String shopRef = priceInfo.getElementsByClass("hide-blacked")
//                            .get(0).getElementsByTag("a")
//                            .get(0).attr("href");
                    String price = priceInfo.text().split(" ")[0];
                    shops.add(new ShopDto("", BigDecimal.valueOf(Double.parseDouble(price))));
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return shops;
    }




}
