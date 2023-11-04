package com.phollux.tuhome.furniture_inventory.service;

import com.phollux.tuhome.furniture_inventory.domain.FurnitureInventory;
import com.phollux.tuhome.furniture_inventory.model.FurnitureInventoryDTO;
import com.phollux.tuhome.furniture_inventory.repos.FurnitureInventoryRepository;
import com.phollux.tuhome.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class FurnitureInventoryServiceImpl implements FurnitureInventoryService {

    private final FurnitureInventoryRepository furnitureInventoryRepository;

    public FurnitureInventoryServiceImpl(
            final FurnitureInventoryRepository furnitureInventoryRepository) {
        this.furnitureInventoryRepository = furnitureInventoryRepository;
    }

    @Override
    public List<FurnitureInventoryDTO> findAll() {
        final List<FurnitureInventory> furnitureInventories = furnitureInventoryRepository.findAll(Sort.by("id"));
        return furnitureInventories.stream()
                .map(furnitureInventory -> mapToDTO(furnitureInventory, new FurnitureInventoryDTO()))
                .toList();
    }

    @Override
    public FurnitureInventoryDTO get(final Integer id) {
        return furnitureInventoryRepository.findById(id)
                .map(furnitureInventory -> mapToDTO(furnitureInventory, new FurnitureInventoryDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Integer create(final FurnitureInventoryDTO furnitureInventoryDTO) {
        final FurnitureInventory furnitureInventory = new FurnitureInventory();
        mapToEntity(furnitureInventoryDTO, furnitureInventory);
        return furnitureInventoryRepository.save(furnitureInventory).getId();
    }

    @Override
    public void update(final Integer id, final FurnitureInventoryDTO furnitureInventoryDTO) {
        final FurnitureInventory furnitureInventory = furnitureInventoryRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(furnitureInventoryDTO, furnitureInventory);
        furnitureInventoryRepository.save(furnitureInventory);
    }

    @Override
    public void delete(final Integer id) {
        furnitureInventoryRepository.deleteById(id);
    }

    private FurnitureInventoryDTO mapToDTO(final FurnitureInventory furnitureInventory,
            final FurnitureInventoryDTO furnitureInventoryDTO) {
        furnitureInventoryDTO.setId(furnitureInventory.getId());
        furnitureInventoryDTO.setFurnitureType(furnitureInventory.getFurnitureType());
        furnitureInventoryDTO.setFurnitureName(furnitureInventory.getFurnitureName());
        furnitureInventoryDTO.setQuantity(furnitureInventory.getQuantity());
        furnitureInventoryDTO.setCondition(furnitureInventory.getCondition());
        return furnitureInventoryDTO;
    }

    private FurnitureInventory mapToEntity(final FurnitureInventoryDTO furnitureInventoryDTO,
            final FurnitureInventory furnitureInventory) {
        furnitureInventory.setFurnitureType(furnitureInventoryDTO.getFurnitureType());
        furnitureInventory.setFurnitureName(furnitureInventoryDTO.getFurnitureName());
        furnitureInventory.setQuantity(furnitureInventoryDTO.getQuantity());
        furnitureInventory.setCondition(furnitureInventoryDTO.getCondition());
        return furnitureInventory;
    }

}
