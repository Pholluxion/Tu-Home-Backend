package com.cdc.tuhome.service.impl;

import com.cdc.tuhome.dto.PropertiesDTO;
import com.cdc.tuhome.mappers.PropertiesMapper;
import com.cdc.tuhome.model.Properties;
import com.cdc.tuhome.repository.IPropertiesRepository;
import com.cdc.tuhome.service.interfaces.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertiesServiceImpl implements IPropertiesService {
    private IPropertiesRepository propertiesRepository;

    @Autowired
    public void setPropertyRepository (IPropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }

    @Override
    public PropertiesDTO createProperty(PropertiesDTO property) {
        Properties newProperty = PropertiesMapper.INSTANCE.toProperties(property);
        newProperty = propertiesRepository.save(newProperty);
        return PropertiesMapper.INSTANCE.toPropertiesDTO(newProperty);
    }

    @Override
    public List<PropertiesDTO> getProperties() {
        List<Properties> properties = propertiesRepository.findAll();
        return properties.stream().map(PropertiesMapper.INSTANCE::toPropertiesDTO).collect(Collectors.toList());
        }

    @Override
    public PropertiesDTO getPropertyById(Long id) {
        Properties property = propertiesRepository.findById((id)).orElse(null);
        return PropertiesMapper.INSTANCE.toPropertiesDTO(property);
    }

    @Override
    public PropertiesDTO updateProperty(Long id, PropertiesDTO updatedProperty) {
        Properties existingProperty = propertiesRepository.findById(id).orElse(null);
        if (existingProperty != null) {
            existingProperty.setAddress(updatedProperty.getAddress());
            existingProperty.setAvailabilityDate(updatedProperty.getAvailabilityDate());
            existingProperty.setAvailabilityStatus(updatedProperty.getAvailabilityStatus());
            existingProperty.setDescription(updatedProperty.getDescription());
            //existingProperty.setInventory(updatedProperty.getInventory());
            existingProperty.setPropertyType(updatedProperty.getPropertyType());

            existingProperty = propertiesRepository.save(existingProperty);

            return PropertiesMapper.INSTANCE.toPropertiesDTO(existingProperty);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteProperty(Long id) {
        if (propertiesRepository.existsById(id)) {
            propertiesRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}