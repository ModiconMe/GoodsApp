package io.modicon.userservice.repository;

import io.modicon.userservice.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, String> {
}
