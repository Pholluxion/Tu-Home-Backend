package com.phollux.tuhome.service;

import com.phollux.tuhome.domain.User;
import com.phollux.tuhome.model.RegistrationRequest;
import com.phollux.tuhome.repos.RoleRepository;
import com.phollux.tuhome.repos.UserRepository;
import com.phollux.tuhome.util.UserRoles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public RegistrationService(final UserRepository userRepository,
            final PasswordEncoder passwordEncoder, final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public boolean emailExists(final RegistrationRequest registrationRequest) {
        return userRepository.existsByEmailIgnoreCase(registrationRequest.getEmail());
    }

    public void register(final RegistrationRequest registrationRequest) {
        log.info("registering new user: {}", registrationRequest.getEmail());

        final User user = new User();
        user.setName(registrationRequest.getName());
        user.setSurname(registrationRequest.getSurname());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setDocumentNumber(registrationRequest.getDocumentNumber());
        // assign default role
        user.setRole(roleRepository.findByName(UserRoles.USER));
        // TODO assign missing relations
        userRepository.save(user);
    }

}
