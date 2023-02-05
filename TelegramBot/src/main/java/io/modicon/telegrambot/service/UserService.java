package io.modicon.telegrambot.service;

import io.modicon.telegrambot.client.UserServiceClient;
import io.modicon.userservice.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserServiceClient userServiceClient;

    public UserDto registerUser(UserDto user) {
        return userServiceClient.createUser(user);
    }

    public UserDto addItem(String item) {
        userServiceClient.addItem()
    }

}
