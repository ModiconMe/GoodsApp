package io.modicon.userservice.service;

import io.modicon.userservice.api.dto.UserDto;
import io.modicon.userservice.model.UserEntity;

public class UserMapper {

    public static UserEntity mapToEntity(UserDto userDto) {
        return UserEntity.builder()
                .userId(userDto.userId())
                .username(userDto.username())
                .build();
    }

    public static UserDto mapToDto(UserEntity userEntity) {
        return UserDto.builder()
                .userId(userEntity.getUserId())
                .username(userEntity.getUsername())
                .build();
    }

}
