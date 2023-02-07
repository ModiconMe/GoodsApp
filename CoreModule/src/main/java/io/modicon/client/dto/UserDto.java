package io.modicon.client.dto;

import lombok.Builder;

@Builder
public record UserDto(
        String userId,
        String username
) {
}
