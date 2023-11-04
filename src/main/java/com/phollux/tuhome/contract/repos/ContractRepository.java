package com.phollux.tuhome.contract.repos;

import com.phollux.tuhome.contract.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
