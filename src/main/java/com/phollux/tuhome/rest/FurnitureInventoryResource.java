package com.phollux.tuhome.rest;

import com.phollux.tuhome.model.FurnitureInventoryDTO;
import com.phollux.tuhome.service.FurnitureInventoryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/furnitureInventories", produces = MediaType.APPLICATION_JSON_VALUE)
public class FurnitureInventoryResource {

    private final FurnitureInventoryService furnitureInventoryService;

    public FurnitureInventoryResource(final FurnitureInventoryService furnitureInventoryService) {
        this.furnitureInventoryService = furnitureInventoryService;
    }

    @GetMapping
    public ResponseEntity<List<FurnitureInventoryDTO>> getAllFurnitureInventories() {
        return ResponseEntity.ok(furnitureInventoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FurnitureInventoryDTO> getFurnitureInventory(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(furnitureInventoryService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createFurnitureInventory(
            @RequestBody @Valid final FurnitureInventoryDTO furnitureInventoryDTO) {
        final Integer createdId = furnitureInventoryService.create(furnitureInventoryDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateFurnitureInventory(
            @PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final FurnitureInventoryDTO furnitureInventoryDTO) {
        furnitureInventoryService.update(id, furnitureInventoryDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteFurnitureInventory(
            @PathVariable(name = "id") final Integer id) {
        furnitureInventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
