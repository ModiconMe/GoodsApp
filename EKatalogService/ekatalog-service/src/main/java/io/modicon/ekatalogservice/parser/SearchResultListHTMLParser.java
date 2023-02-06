package io.modicon.ekatalogservice.parser;

import io.modicon.ekatalogservice.api.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SearchResultListHTMLParser implements EkatalogHTMLParser<List<ItemDto>> {

    private static final String MODEL_SHORT_DIV = "model-short-div list-item--goods";
    private static final String MODEL_SHORT_TITLE = "model-short-title no-u";

    public List<ItemDto> parse(Document html) {
        List<ItemDto> items = new ArrayList<>();

        Elements foundPositions = html.getElementsByClass(MODEL_SHORT_DIV);
        // GoodDto assemble
        foundPositions.forEach(p -> {
            // get goodName
            Elements positionLabel = p.getElementsByClass(MODEL_SHORT_TITLE);
            String positionLabelText = positionLabel.get(0).getElementsByClass("u").text();
            String positionLabelSubtext = positionLabel.get(0).getElementsByClass("list-conf-name ib nobr").text();

            // get shopRef
            String positionRef = SITE_URL + positionLabel.attr("href");

            // get image
            String imageRef = "https://n-katalog.ru" + p
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

            ItemDto item = ItemDto.builder()
                    .name(positionLabelSubtext.length() < 10 ? positionLabelText + " " + positionLabelSubtext : positionLabelText)
                    .itemRef(positionRef)
                    .priceFrom(priceFrom)
                    .priceTo(priceTo)
                    .img(imageRef)
                    .build();
            items.add(item);
        });

        return items;
    }

}
