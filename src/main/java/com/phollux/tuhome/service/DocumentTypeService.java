package com.phollux.tuhome.service;

import com.phollux.tuhome.model.DocumentTypeDTO;
import java.util.List;


public interface DocumentTypeService {

    List<DocumentTypeDTO> findAll();

    DocumentTypeDTO get(Long id);

    Long create(DocumentTypeDTO documentTypeDTO);

    void update(Long id, DocumentTypeDTO documentTypeDTO);

    void delete(Long id);

    boolean nameExists(String name);

    boolean abbreviationExists(String abbreviation);

    String getReferencedWarning(Long id);

}
