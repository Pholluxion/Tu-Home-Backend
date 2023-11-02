package com.phollux.tuhome.service;

import com.phollux.tuhome.model.TypeOfPropertyDTO;
import java.util.List;


public interface TypeOfPropertyService {

    List<TypeOfPropertyDTO> findAll();

    TypeOfPropertyDTO get(Integer id);

    Integer create(TypeOfPropertyDTO typeOfPropertyDTO);

    void update(Integer id, TypeOfPropertyDTO typeOfPropertyDTO);

    void delete(Integer id);

    String getReferencedWarning(Integer id);

}
