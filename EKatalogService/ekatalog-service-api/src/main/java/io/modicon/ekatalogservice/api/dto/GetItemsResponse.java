package io.modicon.ekatalogservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetItemsResponse {
    private List<ItemDto> items;
}
