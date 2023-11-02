package com.phollux.tuhome.repos;

import com.phollux.tuhome.domain.FurnitureInventory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FurnitureInventoryRepository extends JpaRepository<FurnitureInventory, Integer> {
}
