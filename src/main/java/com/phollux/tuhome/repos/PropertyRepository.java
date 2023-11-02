package com.phollux.tuhome.repos;

import com.phollux.tuhome.domain.FurnitureInventory;
import com.phollux.tuhome.domain.Property;
import com.phollux.tuhome.domain.TypeOfProperty;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PropertyRepository extends JpaRepository<Property, Integer> {

    Property findFirstByTypeOfProperty(TypeOfProperty typeOfProperty);

    Property findFirstByFurnitureInventory(FurnitureInventory furnitureInventory);

}
