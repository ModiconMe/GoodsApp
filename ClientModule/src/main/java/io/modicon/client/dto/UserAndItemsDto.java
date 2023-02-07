package io.modicon.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserAndItemsDto {
    private List<UserItemPriceDto> userWithItemsList;
}
