package com.phollux.tuhome.service;

import com.phollux.tuhome.domain.Contract;
import com.phollux.tuhome.domain.DocumentType;
import com.phollux.tuhome.domain.Role;
import com.phollux.tuhome.domain.User;
import com.phollux.tuhome.model.UserDTO;
import com.phollux.tuhome.repos.ContractRepository;
import com.phollux.tuhome.repos.DocumentTypeRepository;
import com.phollux.tuhome.repos.RoleRepository;
import com.phollux.tuhome.repos.UserRepository;
import com.phollux.tuhome.util.NotFoundException;
import com.phollux.tuhome.util.WebUtils;
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
    private final ContractRepository contractRepository;

    public UserServiceImpl(final UserRepository userRepository, final RoleRepository roleRepository,
            final DocumentTypeRepository documentTypeRepository,
            final PasswordEncoder passwordEncoder, final ContractRepository contractRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.passwordEncoder = passwordEncoder;
        this.contractRepository = contractRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
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

    @Override
    public String getReferencedWarning(final Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Contract tenantContract = contractRepository.findFirstByTenant(user);
        if (tenantContract != null) {
            return WebUtils.getMessage("user.contract.tenant.referenced", tenantContract.getId());
        }
        return null;
    }

}
