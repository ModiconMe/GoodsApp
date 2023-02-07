package io.modicon.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@ToString
@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id
    private String userId;
    private String username;
}
