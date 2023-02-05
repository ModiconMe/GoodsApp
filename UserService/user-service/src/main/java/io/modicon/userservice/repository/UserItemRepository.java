package io.modicon.userservice.repository;

import io.modicon.userservice.model.UserEntity;
import io.modicon.userservice.model.UserItemId;
import io.modicon.userservice.model.UserItemPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserItemRepository extends JpaRepository<UserItemPriceEntity, UserItemId> {
    List<UserItemPriceEntity> findByUser(UserEntity user);
}
