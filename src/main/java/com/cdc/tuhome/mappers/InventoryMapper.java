package com.cdc.tuhome.mappers;

import com.cdc.tuhome.dto.InventoryDTO;
import com.cdc.tuhome.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    InventoryDTO toInventoryDTO(Inventory inventory);

    Inventory toInventory(InventoryDTO inventoryDTO);
}
