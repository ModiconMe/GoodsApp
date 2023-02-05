package io.modicon.ekatalogservice.parser;

import io.modicon.ekatalogservice.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class EKatalogClient {

    public Document getHTML(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw ApiException.exception(HttpStatus.BAD_REQUEST, "url %s not found", url);
        }
    }

}
