package com.cdc.tuhome.controller;

import com.cdc.tuhome.dto.InventoryItemDTO;
import com.cdc.tuhome.service.interfaces.IInventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventoryitem")
public class InventoryItemController {
    private IInventoryItemService inventoryItemService;
    
    @Autowired
    public void setInventoryItemService(IInventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<InventoryItemDTO> createInventoryItem(@RequestBody InventoryItemDTO inventoryItemDTO) {
        InventoryItemDTO createdInventoryItem = inventoryItemService.createInventoryItem(inventoryItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInventoryItem);
    }
    
    @GetMapping
    public ResponseEntity<List<InventoryItemDTO>> getInventoriesItem() {
        return ResponseEntity.ok(inventoryItemService.getInventoriesItem());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InventoryItemDTO> getInventoryItemById(@PathVariable Long id) {
        InventoryItemDTO inventoryItem = this.inventoryItemService.getInventoryItemById(id);
        return ResponseEntity.ok(inventoryItem);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InventoryItemDTO> updateInventoryItem(@PathVariable Long id, @RequestBody InventoryItemDTO inventoryItem) {
        InventoryItemDTO updatedInventoryItem = inventoryItemService.updateInventoryItem(id, inventoryItem);
        if (updatedInventoryItem != null) {
            return ResponseEntity.ok(updatedInventoryItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteInventoryItem(@PathVariable Long id) { 
        return ResponseEntity.ok(inventoryItemService.deleteInventoryItem(id));
    }
}
