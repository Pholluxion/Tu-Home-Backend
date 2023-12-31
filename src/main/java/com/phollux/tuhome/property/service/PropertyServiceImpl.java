package com.phollux.tuhome.property.service;

import com.phollux.tuhome.furniture_inventory.domain.FurnitureInventory;
import com.phollux.tuhome.furniture_inventory.repos.FurnitureInventoryRepository;
import com.phollux.tuhome.property.domain.Property;
import com.phollux.tuhome.property.model.PropertyDTO;
import com.phollux.tuhome.property.repos.PropertyRepository;
import com.phollux.tuhome.type_of_property.domain.TypeOfProperty;
import com.phollux.tuhome.type_of_property.repos.TypeOfPropertyRepository;
import com.phollux.tuhome.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final TypeOfPropertyRepository typeOfPropertyRepository;
    private final FurnitureInventoryRepository furnitureInventoryRepository;

    public PropertyServiceImpl(final PropertyRepository propertyRepository,
            final TypeOfPropertyRepository typeOfPropertyRepository,
            final FurnitureInventoryRepository furnitureInventoryRepository) {
        this.propertyRepository = propertyRepository;
        this.typeOfPropertyRepository = typeOfPropertyRepository;
        this.furnitureInventoryRepository = furnitureInventoryRepository;
    }

    @Override
    public List<PropertyDTO> findAll() {
        final List<Property> properties = propertyRepository.findAll(Sort.by("id"));
        return properties.stream()
                .map(property -> mapToDTO(property, new PropertyDTO()))
                .toList();
    }

    @Override
    public PropertyDTO get(final Integer id) {
        return propertyRepository.findById(id)
                .map(property -> mapToDTO(property, new PropertyDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Integer create(final PropertyDTO propertyDTO) {
        final Property property = new Property();
        mapToEntity(propertyDTO, property);
        return propertyRepository.save(property).getId();
    }

    @Override
    public void update(final Integer id, final PropertyDTO propertyDTO) {
        final Property property = propertyRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(propertyDTO, property);
        propertyRepository.save(property);
    }

    @Override
    public void delete(final Integer id) {
        propertyRepository.deleteById(id);
    }

    private PropertyDTO mapToDTO(final Property property, final PropertyDTO propertyDTO) {
        propertyDTO.setId(property.getId());
        propertyDTO.setName(property.getName());
        propertyDTO.setAddress(property.getAddress());
        propertyDTO.setPrice(property.getPrice());
        propertyDTO.setSquareFeet(property.getSquareFeet());
        propertyDTO.setBedrooms(property.getBedrooms());
        propertyDTO.setBathrooms(property.getBathrooms());
        propertyDTO.setGarage(property.getGarage());
        propertyDTO.setPatio(property.getPatio());
        propertyDTO.setElevator(property.getElevator());
        propertyDTO.setTypeOfProperty(property.getTypeOfProperty() == null ? null : property.getTypeOfProperty().getId());
        propertyDTO.setFurnitureInventory(property.getFurnitureInventory() == null ? null : property.getFurnitureInventory().getId());
        return propertyDTO;
    }

    private Property mapToEntity(final PropertyDTO propertyDTO, final Property property) {
        property.setName(propertyDTO.getName());
        property.setAddress(propertyDTO.getAddress());
        property.setPrice(propertyDTO.getPrice());
        property.setSquareFeet(propertyDTO.getSquareFeet());
        property.setBedrooms(propertyDTO.getBedrooms());
        property.setBathrooms(propertyDTO.getBathrooms());
        property.setGarage(propertyDTO.getGarage());
        property.setPatio(propertyDTO.getPatio());
        property.setElevator(propertyDTO.getElevator());
        final TypeOfProperty typeOfProperty = propertyDTO.getTypeOfProperty() == null ? null : typeOfPropertyRepository.findById(propertyDTO.getTypeOfProperty())
                .orElseThrow(() -> new NotFoundException("typeOfProperty not found"));
        property.setTypeOfProperty(typeOfProperty);
        final FurnitureInventory furnitureInventory = propertyDTO.getFurnitureInventory() == null ? null : furnitureInventoryRepository.findById(propertyDTO.getFurnitureInventory())
                .orElseThrow(() -> new NotFoundException("furnitureInventory not found"));
        property.setFurnitureInventory(furnitureInventory);
        return property;
    }

}
