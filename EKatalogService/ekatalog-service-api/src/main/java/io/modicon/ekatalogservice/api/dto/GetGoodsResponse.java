package io.modicon.ekatalogservice.api.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetGoodsResponse {
    public List<GoodDto> goods;
}
