package com.cdc.tuhome.controller;

import com.cdc.tuhome.dto.PropertiesDTO;
import com.cdc.tuhome.service.interfaces.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/properties")
public class PropertiesController {
    private IPropertiesService propertiesService;
    
    @Autowired
    public void setPropertiesService(IPropertiesService propertyService) {
        this.propertiesService = propertyService; 
    }
    
    @PostMapping("/create")
    public ResponseEntity<PropertiesDTO> createProperty(@RequestBody PropertiesDTO propertyDTO) {
        PropertiesDTO createdProperty = propertiesService.createProperty(propertyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
    }
    
    @GetMapping
    public ResponseEntity<List<PropertiesDTO>> getProperties() {
        return ResponseEntity.ok(propertiesService.getProperties());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PropertiesDTO> getPropertyById(@PathVariable Long id) {
        PropertiesDTO propertyDTO = this.propertiesService.getPropertyById(id);
        return ResponseEntity.ok(propertyDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PropertiesDTO> updateProperty(@PathVariable Long id, @RequestBody PropertiesDTO property) {
        PropertiesDTO updatedProperty = propertiesService.updateProperty(id, property);
        if (updatedProperty != null) {
            return ResponseEntity.ok(updatedProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProperty(@PathVariable Long id) {
        return ResponseEntity.ok(propertiesService.deleteProperty(id));
    }
}
