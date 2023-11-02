package com.phollux.tuhome.service;

import com.phollux.tuhome.model.PropertyDTO;
import java.util.List;


public interface PropertyService {

    List<PropertyDTO> findAll();

    PropertyDTO get(Integer id);

    Integer create(PropertyDTO propertyDTO);

    void update(Integer id, PropertyDTO propertyDTO);

    void delete(Integer id);

    String getReferencedWarning(Integer id);

}
