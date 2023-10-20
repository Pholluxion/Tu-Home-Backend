package com.cdc.tuhome.repository;

import com.cdc.tuhome.model.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPropertiesRepository extends JpaRepository<Properties, Long> {
}
