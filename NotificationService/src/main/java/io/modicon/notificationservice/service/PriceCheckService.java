package io.modicon.notificationservice.service;

import io.modicon.client.dto.ItemDto;
import io.modicon.client.dto.NotificationMessage;
import io.modicon.client.dto.UrlDto;
import io.modicon.client.dto.UserItemPriceDto;
import io.modicon.client.feign.EKatalogServiceClient;
import io.modicon.client.feign.TelegramServiceClient;
import io.modicon.client.feign.UserServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PriceCheckService {

    private final EKatalogServiceClient eKatalogFeignClient;
    private final UserServiceClient userServiceClient;
    private final TelegramServiceClient telegramServiceClient;

    public void checkAndSend() {
        log.info("check prices");
        List<UserItemPriceDto> userItemsList = userServiceClient.getUsersAndItems().getUserWithItemsList();

        if (userItemsList != null && !userItemsList.isEmpty()) {
            for (UserItemPriceDto uip : userItemsList) {
                ItemDto item = eKatalogFeignClient.getItemsWithPrices(new UrlDto(uip.itemId())).getItem();
                if (item.priceFrom() != null && item.priceFrom().compareTo(uip.priceToCheck()) <= 0 && !uip.happened()) {
                    telegramServiceClient.sendMessage(new NotificationMessage(String
                            .format("Hello your item [%s] price less than %s", uip.itemId(), uip.priceToCheck())), uip.userId());
                    userServiceClient.saveUserItemPrice(new UserItemPriceDto(uip.userId(), uip.itemId(), uip.priceToCheck(), true));
                } else if ((item.priceFrom() == null || item.priceFrom().compareTo(uip.priceToCheck()) > 0) && uip.happened()){
                    userServiceClient.saveUserItemPrice(new UserItemPriceDto(uip.userId(), uip.itemId(), uip.priceToCheck(), false));
                }
            }
        }
    }
}
