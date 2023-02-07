package io.modicon.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetItemAndShopsResponse {
    private ItemDto item;
    private List<ShopDto> shops;
}
