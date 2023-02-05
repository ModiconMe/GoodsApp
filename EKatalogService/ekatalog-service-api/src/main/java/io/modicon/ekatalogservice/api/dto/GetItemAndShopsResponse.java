package io.modicon.ekatalogservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetItemAndShopsResponse {
    private ItemDto item;
    private List<ShopDto> shops;
}
