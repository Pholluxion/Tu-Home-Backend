package com.phollux.tuhome.contract.repos;

import com.phollux.tuhome.contract.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ContractRepository extends JpaRepository<Contract, Integer> {

    List<Contract> findByUserId(Long tenantId);
    List<Contract> findByPropertyId(Integer landlordId);

}
