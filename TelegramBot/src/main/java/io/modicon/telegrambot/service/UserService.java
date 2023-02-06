package io.modicon.telegrambot.service;

import io.modicon.ekatalogservice.api.dto.UrlDto;
import io.modicon.telegrambot.client.UserServiceClient;
import io.modicon.userservice.api.dto.AddItemToUser;
import io.modicon.userservice.api.dto.ItemWithPricesDto;
import io.modicon.userservice.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserServiceClient userServiceClient;

    public UserDto registerUser(UserDto user) {
        return userServiceClient.createUser(user);
    }

    public void addItem(String itemUrl, BigDecimal price, String userId) {
        userServiceClient.addItem(new AddItemToUser(itemUrl, price), userId);
    }

    public void deleteItem(String itemUrl, String userId) {
        userServiceClient.deleteItem(new UrlDto(itemUrl), userId);
    }

    public List<ItemWithPricesDto> getItems(String userId) {
        return userServiceClient.getUsersItem(userId);
    }

}
