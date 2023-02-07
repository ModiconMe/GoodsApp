package io.modicon.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserItemPriceEntity {
    @EmbeddedId
    private UserItemId userItemId;

    @ManyToOne
    @MapsId("userId")
    private UserEntity user;

    @ManyToOne
    @MapsId("itemId")
    private ItemEntity item;

    private BigDecimal priceToCheck;
    private Boolean happened;
}
