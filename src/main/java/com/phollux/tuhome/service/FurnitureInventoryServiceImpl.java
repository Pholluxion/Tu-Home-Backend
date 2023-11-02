package com.phollux.tuhome.service;

import com.phollux.tuhome.domain.FurnitureInventory;
import com.phollux.tuhome.domain.Property;
import com.phollux.tuhome.model.FurnitureInventoryDTO;
import com.phollux.tuhome.repos.FurnitureInventoryRepository;
import com.phollux.tuhome.repos.PropertyRepository;
import com.phollux.tuhome.util.NotFoundException;
import com.phollux.tuhome.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class FurnitureInventoryServiceImpl implements FurnitureInventoryService {

    private final FurnitureInventoryRepository furnitureInventoryRepository;
    private final PropertyRepository propertyRepository;

    public FurnitureInventoryServiceImpl(
            final FurnitureInventoryRepository furnitureInventoryRepository,
            final PropertyRepository propertyRepository) {
        this.furnitureInventoryRepository = furnitureInventoryRepository;
        this.propertyRepository = propertyRepository;
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

    @Override
    public String getReferencedWarning(final Integer id) {
        final FurnitureInventory furnitureInventory = furnitureInventoryRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Property furnitureInventoryProperty = propertyRepository.findFirstByFurnitureInventory(furnitureInventory);
        if (furnitureInventoryProperty != null) {
            return WebUtils.getMessage("furnitureInventory.property.furnitureInventory.referenced", furnitureInventoryProperty.getId());
        }
        return null;
    }

}
