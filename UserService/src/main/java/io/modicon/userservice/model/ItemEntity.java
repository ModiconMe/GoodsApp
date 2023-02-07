package io.modicon.userservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemEntity {
    @Id
    private String ref;
    private String name;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    @Lob
    private String img;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShopEntity> shops;
}
