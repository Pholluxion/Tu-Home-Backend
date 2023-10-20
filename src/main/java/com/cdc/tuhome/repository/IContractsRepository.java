package com.cdc.tuhome.repository;

import com.cdc.tuhome.model.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContractsRepository extends JpaRepository<Contracts, Long> {
}
