package com.phollux.tuhome.service;

import com.phollux.tuhome.model.FurnitureInventoryDTO;
import java.util.List;


public interface FurnitureInventoryService {

    List<FurnitureInventoryDTO> findAll();

    FurnitureInventoryDTO get(Integer id);

    Integer create(FurnitureInventoryDTO furnitureInventoryDTO);

    void update(Integer id, FurnitureInventoryDTO furnitureInventoryDTO);

    void delete(Integer id);

    String getReferencedWarning(Integer id);

}
