package io.modicon.userservice.api.operation;

import io.modicon.userservice.api.dto.AddItemToUser;
import io.modicon.userservice.api.dto.ItemWithPricesDto;
import io.modicon.userservice.api.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserOperation {

    @PostMapping
    UserDto createUser(@RequestBody UserDto user);

    @DeleteMapping("{userId}")
    UserDto deleteUser(@PathVariable String userId);

    @PostMapping("/items/{userId}")
    ItemWithPricesDto addItem(@RequestBody AddItemToUser userGood, @PathVariable String userId);

    @DeleteMapping("/items/{userId}/{itemUrl}")
    void deleteItem(@PathVariable String itemUrl, @PathVariable String userId);

    @GetMapping("/items/{userId}")
    List<ItemWithPricesDto> getUsersItem(@PathVariable String userId);

}
