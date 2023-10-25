package com.cdc.tuhome.service.interfaces;

import com.cdc.tuhome.dto.PropertiesDTO;
import java.util.List;

public interface IPropertiesService {
    
    PropertiesDTO createProperty(PropertiesDTO property);
    
    List<PropertiesDTO> getProperties();
    
    PropertiesDTO getPropertyById(Long id);
    
    PropertiesDTO updateProperty(Long id, PropertiesDTO property);
    
    Boolean deleteProperty(Long id);
    
}
