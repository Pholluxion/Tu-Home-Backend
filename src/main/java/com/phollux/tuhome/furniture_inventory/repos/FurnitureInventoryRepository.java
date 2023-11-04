package com.phollux.tuhome.furniture_inventory.repos;

import com.phollux.tuhome.furniture_inventory.domain.FurnitureInventory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FurnitureInventoryRepository extends JpaRepository<FurnitureInventory, Integer> {
}
