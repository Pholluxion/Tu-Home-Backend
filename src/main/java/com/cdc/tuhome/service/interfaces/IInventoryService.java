package com.cdc.tuhome.service.interfaces;

import com.cdc.tuhome.dto.InventoryDTO;
import java.util.List;

public interface IInventoryService {
    
    InventoryDTO createInventory(InventoryDTO inventory);
    
    List<InventoryDTO> getInventories();
    
    InventoryDTO getInventoryById(Long id);
    
    InventoryDTO updateInventory(Long id, InventoryDTO inventory);
    
    Boolean deleteInventory(Long id);
    
}
