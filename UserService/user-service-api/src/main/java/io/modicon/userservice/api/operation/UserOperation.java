package io.modicon.userservice.api.operation;

import io.modicon.ekatalogservice.api.dto.UrlDto;
import io.modicon.userservice.api.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserOperation {

    @PostMapping
    UserDto createUser(@RequestBody UserDto user);

    @DeleteMapping("{userId}")
    UserDto deleteUser(@PathVariable String userId);

    @PostMapping("/items/{userId}")
    ItemWithPricesDto addItem(@RequestBody AddItemToUser userGood, @PathVariable String userId);

    @DeleteMapping("/items/{userId}")
    void deleteItem(@RequestBody UrlDto itemUrl, @PathVariable String userId);

    @GetMapping("/items/{userId}")
    List<ItemWithPricesDto> getUsersItem(@PathVariable String userId);

    @GetMapping
    UserAndItemsDto getUsersAndItems();

    @PostMapping("/items")
    void saveUserItemPrice(@RequestBody UserItemPriceDto userItemPriceDto);

}
