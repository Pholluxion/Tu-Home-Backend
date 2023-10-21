package com.cdc.tuhome.mappers;

import com.cdc.tuhome.dto.InventoryItemDTO;
import com.cdc.tuhome.model.InventoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryItemMapper {
    InventoryItemMapper INSTANCE = Mappers.getMapper(InventoryItemMapper.class);

    InventoryItemDTO toInventoryItemDTO(InventoryItem inventoryItem);

    InventoryItem toInventoryItem(InventoryItemDTO inventoryItemDTO);
}
