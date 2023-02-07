package io.modicon.userservice.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserItemId implements Serializable {
    private String userId;
    private String itemId;
}
