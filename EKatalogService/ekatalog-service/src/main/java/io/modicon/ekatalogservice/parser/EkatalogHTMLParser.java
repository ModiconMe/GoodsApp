package io.modicon.ekatalogservice.parser;

import org.jsoup.nodes.Document;

public interface EkatalogHTMLParser<T> {

    String SITE_URL = "https://n-katalog.ru";

    T parse(Document html);

}
