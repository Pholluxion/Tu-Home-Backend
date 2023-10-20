package com.cdc.tuhome.repository;

import com.cdc.tuhome.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
