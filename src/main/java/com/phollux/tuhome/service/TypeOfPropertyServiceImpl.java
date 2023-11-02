package com.phollux.tuhome.service;

import com.phollux.tuhome.domain.Property;
import com.phollux.tuhome.domain.TypeOfProperty;
import com.phollux.tuhome.model.TypeOfPropertyDTO;
import com.phollux.tuhome.repos.PropertyRepository;
import com.phollux.tuhome.repos.TypeOfPropertyRepository;
import com.phollux.tuhome.util.NotFoundException;
import com.phollux.tuhome.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TypeOfPropertyServiceImpl implements TypeOfPropertyService {

    private final TypeOfPropertyRepository typeOfPropertyRepository;
    private final PropertyRepository propertyRepository;

    public TypeOfPropertyServiceImpl(final TypeOfPropertyRepository typeOfPropertyRepository,
            final PropertyRepository propertyRepository) {
        this.typeOfPropertyRepository = typeOfPropertyRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<TypeOfPropertyDTO> findAll() {
        final List<TypeOfProperty> typeOfProperties = typeOfPropertyRepository.findAll(Sort.by("id"));
        return typeOfProperties.stream()
                .map(typeOfProperty -> mapToDTO(typeOfProperty, new TypeOfPropertyDTO()))
                .toList();
    }

    @Override
    public TypeOfPropertyDTO get(final Integer id) {
        return typeOfPropertyRepository.findById(id)
                .map(typeOfProperty -> mapToDTO(typeOfProperty, new TypeOfPropertyDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Integer create(final TypeOfPropertyDTO typeOfPropertyDTO) {
        final TypeOfProperty typeOfProperty = new TypeOfProperty();
        mapToEntity(typeOfPropertyDTO, typeOfProperty);
        return typeOfPropertyRepository.save(typeOfProperty).getId();
    }

    @Override
    public void update(final Integer id, final TypeOfPropertyDTO typeOfPropertyDTO) {
        final TypeOfProperty typeOfProperty = typeOfPropertyRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(typeOfPropertyDTO, typeOfProperty);
        typeOfPropertyRepository.save(typeOfProperty);
    }

    @Override
    public void delete(final Integer id) {
        typeOfPropertyRepository.deleteById(id);
    }

    private TypeOfPropertyDTO mapToDTO(final TypeOfProperty typeOfProperty,
            final TypeOfPropertyDTO typeOfPropertyDTO) {
        typeOfPropertyDTO.setId(typeOfProperty.getId());
        typeOfPropertyDTO.setName(typeOfProperty.getName());
        typeOfPropertyDTO.setDescription(typeOfProperty.getDescription());
        return typeOfPropertyDTO;
    }

    private TypeOfProperty mapToEntity(final TypeOfPropertyDTO typeOfPropertyDTO,
            final TypeOfProperty typeOfProperty) {
        typeOfProperty.setName(typeOfPropertyDTO.getName());
        typeOfProperty.setDescription(typeOfPropertyDTO.getDescription());
        return typeOfProperty;
    }

    @Override
    public String getReferencedWarning(final Integer id) {
        final TypeOfProperty typeOfProperty = typeOfPropertyRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Property typeOfPropertyProperty = propertyRepository.findFirstByTypeOfProperty(typeOfProperty);
        if (typeOfPropertyProperty != null) {
            return WebUtils.getMessage("typeOfProperty.property.typeOfProperty.referenced", typeOfPropertyProperty.getId());
        }
        return null;
    }

}
