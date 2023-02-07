package io.modicon.client.operation;

import io.modicon.client.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserOperation {

    String route = "api/users";

    @PostMapping(route)
    UserDto createUser(@RequestBody UserDto user);

    @DeleteMapping(route + "/{userId}")
    UserDto deleteUser(@PathVariable String userId);

    @PostMapping(route + "/items/{userId}")
    ItemWithPricesDto addItem(@RequestBody AddItemToUser userGood, @PathVariable String userId);

    @DeleteMapping(route + "/items/{userId}")
    void deleteItem(@RequestBody UrlDto itemUrl, @PathVariable String userId);

    @GetMapping(route + "/items/{userId}")
    List<ItemWithPricesDto> getUsersItem(@PathVariable String userId);

    @GetMapping(route)
    UserAndItemsDto getUsersAndItems();

    @PostMapping(route + "/items")
    void saveUserItemPrice(@RequestBody UserItemPriceDto userItemPriceDto);

}
