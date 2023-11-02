package com.phollux.tuhome.repos;

import com.phollux.tuhome.domain.DocumentType;
import com.phollux.tuhome.domain.Role;
import com.phollux.tuhome.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "role")
    User findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

    User findFirstByRole(Role role);

    User findFirstByDocumentType(DocumentType documentType);

}
