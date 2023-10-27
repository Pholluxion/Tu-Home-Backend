package com.cdc.tuhome.service.interfaces;

import com.cdc.tuhome.dto.InventoryItemDTO;
import java.util.List;

public interface IInventoryItemService {
    
    InventoryItemDTO createInventoryItem(InventoryItemDTO inventoryItemDTO);
    
    List<InventoryItemDTO> getInventoriesItem();
    
    InventoryItemDTO getInventoryItemById(Long id);
    
    InventoryItemDTO updateInventoryItem(Long id, InventoryItemDTO inventoryItemDTO);
    
    Boolean deleteInventoryItem(Long id);

}
