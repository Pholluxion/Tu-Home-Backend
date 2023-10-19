package com.cdc.tuhome.repository;

import com.cdc.tuhome.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventoryRepository extends JpaRepository<Inventory, Long> {
}
