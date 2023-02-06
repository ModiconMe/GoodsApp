package io.modicon.userservice.service;

import io.modicon.userservice.api.dto.*;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto deleteUser(String userId);

    ItemWithPricesDto addItemToUser(AddItemToUser userGood, String userId);

    void deleteUsersItem(String itemUrl, String userId);

    List<ItemWithPricesDto> getUserItems(String userId);

    UserAndItemsDto getUserAndItems();

    void saveUserItemPriceDto(UserItemPriceDto userItemPriceDto);

}
