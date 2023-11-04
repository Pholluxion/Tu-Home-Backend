package com.phollux.tuhome.document_type.repos;

import com.phollux.tuhome.document_type.domain.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

    DocumentType findByName(String name);
    
    boolean existsByNameIgnoreCase(String name);

    boolean existsByAbbreviationIgnoreCase(String abbreviation);

}
