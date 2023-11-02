package com.phollux.tuhome.repos;

import com.phollux.tuhome.domain.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByAbbreviationIgnoreCase(String abbreviation);

}
