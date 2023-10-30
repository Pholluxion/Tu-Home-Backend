package com.cdc.tuhome.service.impl;

import com.cdc.tuhome.dto.InventoryItemDTO;
import com.cdc.tuhome.mappers.InventoryItemMapper;
import com.cdc.tuhome.model.InventoryItem;
import com.cdc.tuhome.repository.IInventoryItemRepository;
import com.cdc.tuhome.service.interfaces.IInventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdc.tuhome.mappers.InventoryMapper;
import com.cdc.tuhome.model.Inventory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryItemServiceImpl implements IInventoryItemService {
    private IInventoryItemRepository inventoryItemRepository;
    
    @Autowired
    public void setInventoryItemRepository(IInventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public InventoryItemDTO createInventoryItem(InventoryItemDTO inventoryItemDTO) {
        InventoryItem newInventoryItem = InventoryItemMapper.INSTANCE.toInventoryItem(inventoryItemDTO);
        newInventoryItem = inventoryItemRepository.save(newInventoryItem);
        return InventoryItemMapper.INSTANCE.toInventoryItemDTO(newInventoryItem);
    }

    @Override
    public List<InventoryItemDTO> getInventoriesItem() {
        List<InventoryItem> inventoriesItem = inventoryItemRepository.findAll();
        return inventoriesItem.stream().map(InventoryItemMapper.INSTANCE::toInventoryItemDTO).collect(Collectors.toList());
    }

    @Override
    public InventoryItemDTO getInventoryItemById(Long id) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(id).orElse(null);
        return InventoryItemMapper.INSTANCE.toInventoryItemDTO(inventoryItem);
    }

    @Override
    public InventoryItemDTO updateInventoryItem(Long id, InventoryItemDTO updatedInventoryItemDTO) {
        InventoryItem existingInventoryItem = inventoryItemRepository.findById(id).orElse(null);
        if (existingInventoryItem != null) {
            
            Inventory updatedInventory = InventoryMapper.INSTANCE.toInventory(updatedInventoryItemDTO.getInventory());
            existingInventoryItem.setInventory(updatedInventory);
            existingInventoryItem.setItemname(updatedInventoryItemDTO.getItemname());
            existingInventoryItem.setQuantity(updatedInventoryItemDTO.getQuantity());
            existingInventoryItem.setValue(updatedInventoryItemDTO.getValue());
            
            existingInventoryItem = inventoryItemRepository.save(existingInventoryItem);
            
            return InventoryItemMapper.INSTANCE.toInventoryItemDTO(existingInventoryItem);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteInventoryItem(Long id) {
        if (inventoryItemRepository.existsById(id)) {
            inventoryItemRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
