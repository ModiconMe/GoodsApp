package io.modicon.ekatalogservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetItemsResponse {
    private List<ItemDto> items;
}
