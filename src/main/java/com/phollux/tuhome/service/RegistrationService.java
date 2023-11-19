package com.phollux.tuhome.service;

import com.phollux.tuhome.document_type.domain.DocumentType;
import com.phollux.tuhome.document_type.repos.DocumentTypeRepository;
import com.phollux.tuhome.model.RegistrationRequest;
import com.phollux.tuhome.role.repos.RoleRepository;
import com.phollux.tuhome.user.domain.User;
import com.phollux.tuhome.user.repos.UserRepository;
import com.phollux.tuhome.util.DocumentTypes;
import com.phollux.tuhome.util.UserRoles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final DocumentTypeRepository documentTypeRepository;

    public RegistrationService(final UserRepository userRepository,
            final PasswordEncoder passwordEncoder, final RoleRepository roleRepository,
                               final DocumentTypeRepository documentTypeRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.documentTypeRepository = documentTypeRepository;
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
        if(registrationRequest.getName().matches(".*(THAdmin).*")){
            user.setRole(roleRepository.findByName(UserRoles.ADMIN));
        }else{
            user.setRole(roleRepository.findByName(UserRoles.USER));
        }
        // assign default Document Type
        if(registrationRequest.getDocumentType() == 0){
            user.setDocumentType(documentTypeRepository.findByName(DocumentTypes.CC));
        }else {
            final long documentTypeId = registrationRequest.getDocumentType();
            final Optional<DocumentType> documentType = documentTypeRepository.findById(documentTypeId);
            if (documentType.isEmpty()) {
                throw new IllegalArgumentException("Invalid document type: " + documentTypeId);
            }
            user.setDocumentType(documentType.get());
        }

        userRepository.save(user);
    }

}
