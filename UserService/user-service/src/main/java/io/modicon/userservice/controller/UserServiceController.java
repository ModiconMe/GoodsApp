package io.modicon.userservice.controller;

import io.modicon.userservice.api.dto.AddItemToUser;
import io.modicon.userservice.api.dto.ItemWithPricesDto;
import io.modicon.userservice.api.dto.UserDto;
import io.modicon.userservice.api.operation.UserOperation;
import io.modicon.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/users")
@RestController
public class UserServiceController implements UserOperation {

    private final UserService userService;

    @Override
    public UserDto createUser(UserDto user) {
        return userService.createUser(user);
    }

    @Override
    public UserDto deleteUser(String userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public ItemWithPricesDto addItem(AddItemToUser userGood, String userId) {
        return userService.addItemToUser(userGood, userId);
    }

    @Override
    public void deleteItem(String itemUrl, String userId) {
        userService.deleteUsersItem(itemUrl, userId);
    }

    @Override
    public List<ItemWithPricesDto> getUsersItem(String userId) {
        return userService.getUserItems(userId);
    }
}
