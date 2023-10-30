package com.cdc.tuhome.service.impl;

import com.cdc.tuhome.dto.InventoryDTO;
import com.cdc.tuhome.mappers.InventoryMapper;
import com.cdc.tuhome.model.Inventory;
import com.cdc.tuhome.repository.IInventoryRepository;
import com.cdc.tuhome.service.interfaces.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements IInventoryService {

    private IInventoryRepository inventoryRepository;
    
    @Autowired
    public void setPropertyRepository (IInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryDTO createInventory(InventoryDTO inventory) {
        Inventory newInventory = InventoryMapper.INSTANCE.toInventory(inventory);
        newInventory = inventoryRepository.save(newInventory);
        return InventoryMapper.INSTANCE.toInventoryDTO(newInventory);
        }

    @Override
    public List<InventoryDTO> getInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(InventoryMapper.INSTANCE::toInventoryDTO).collect(Collectors.toList());
        
    }

    @Override
    public InventoryDTO getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id).orElse(null);
        return InventoryMapper.INSTANCE.toInventoryDTO(inventory);
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO updatedInventoryDTO) {
        Inventory existingInventory = inventoryRepository.findById(id).orElse(null);
        if (existingInventory != null) {
            
            Inventory updatedInventory = InventoryMapper.INSTANCE.toInventory(updatedInventoryDTO);
            
            //existingInventory.setItems(updatedInventory.getItems());
            existingInventory.setProperty(updatedInventory.getProperty());
            
            existingInventory = inventoryRepository.save(existingInventory);
            
            return InventoryMapper.INSTANCE.toInventoryDTO(existingInventory);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteInventory(Long id) {
        if (inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
}
