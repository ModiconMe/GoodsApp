package io.modicon.notificationservice.service;

import io.modicon.ekatalogservice.api.dto.ItemDto;
import io.modicon.ekatalogservice.api.dto.UrlDto;
import io.modicon.notificationservice.client.EKatalogFeignClient;
import io.modicon.notificationservice.client.TelegramServiceClient;
import io.modicon.notificationservice.client.UserServiceClient;
import io.modicon.telegrambot.client.NotificationMessage;
import io.modicon.userservice.api.dto.UserItemPriceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PriceCheckService {

    private final EKatalogFeignClient eKatalogFeignClient;
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
