package com.phollux.tuhome.user.service;

import com.phollux.tuhome.document_type.domain.DocumentType;
import com.phollux.tuhome.document_type.repos.DocumentTypeRepository;
import com.phollux.tuhome.role.domain.Role;
import com.phollux.tuhome.role.repos.RoleRepository;
import com.phollux.tuhome.user.domain.User;
import com.phollux.tuhome.user.model.UserDTO;
import com.phollux.tuhome.user.repos.UserRepository;
import com.phollux.tuhome.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(final UserRepository userRepository, final RoleRepository roleRepository,
            final DocumentTypeRepository documentTypeRepository,
            final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    @Override
    public UserDTO findByEmail(String email) {
        return mapToDTO(userRepository.findByEmailIgnoreCase(email), new UserDTO()) ;
    }

    @Override
    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    @Override
    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    @Override
    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setDocumentNumber(user.getDocumentNumber());
        userDTO.setRole(user.getRole() == null ? null : user.getRole().getId());
        userDTO.setDocumentType(user.getDocumentType() == null ? null : user.getDocumentType().getId());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setDocumentNumber(userDTO.getDocumentNumber());
        final Role role = userDTO.getRole() == null ? null : roleRepository.findById(userDTO.getRole())
                .orElseThrow(() -> new NotFoundException("role not found"));
        user.setRole(role);
        final DocumentType documentType = userDTO.getDocumentType() == null ? null : documentTypeRepository.findById(userDTO.getDocumentType())
                .orElseThrow(() -> new NotFoundException("documentType not found"));
        user.setDocumentType(documentType);
        return user;
    }

}
