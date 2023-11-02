package com.phollux.tuhome.repos;

import com.phollux.tuhome.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    boolean existsByNameIgnoreCase(String name);

}
