package com.phollux.tuhome.property.repos;

import com.phollux.tuhome.property.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PropertyRepository extends JpaRepository<Property, Integer> {
}
