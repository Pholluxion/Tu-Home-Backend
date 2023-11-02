package com.phollux.tuhome.repos;

import com.phollux.tuhome.domain.Contract;
import com.phollux.tuhome.domain.Property;
import com.phollux.tuhome.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContractRepository extends JpaRepository<Contract, Integer> {

    Contract findFirstByTenant(User user);

    Contract findFirstByLandlord(Property property);

}
