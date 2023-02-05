package io.modicon.userservice.api.dto;

import lombok.Builder;

@Builder
public record UserDto(
        String userId,
        String username
) {
}
