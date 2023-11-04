package com.phollux.tuhome.user.repos;

import com.phollux.tuhome.user.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "role")
    User findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

}
