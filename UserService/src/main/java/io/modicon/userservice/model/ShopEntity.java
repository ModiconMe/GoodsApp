package io.modicon.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShopEntity {
    @Id
    private String shopRef;
    private BigDecimal price;
}
