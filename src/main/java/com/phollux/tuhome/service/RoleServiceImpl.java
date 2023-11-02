package com.phollux.tuhome.service;

import com.phollux.tuhome.domain.Role;
import com.phollux.tuhome.domain.User;
import com.phollux.tuhome.model.RoleDTO;
import com.phollux.tuhome.repos.RoleRepository;
import com.phollux.tuhome.repos.UserRepository;
import com.phollux.tuhome.util.NotFoundException;
import com.phollux.tuhome.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleServiceImpl(final RoleRepository roleRepository,
            final UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<RoleDTO> findAll() {
        final List<Role> roles = roleRepository.findAll(Sort.by("id"));
        return roles.stream()
                .map(role -> mapToDTO(role, new RoleDTO()))
                .toList();
    }

    @Override
    public RoleDTO get(final Long id) {
        return roleRepository.findById(id)
                .map(role -> mapToDTO(role, new RoleDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Long create(final RoleDTO roleDTO) {
        final Role role = new Role();
        mapToEntity(roleDTO, role);
        return roleRepository.save(role).getId();
    }

    @Override
    public void update(final Long id, final RoleDTO roleDTO) {
        final Role role = roleRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(roleDTO, role);
        roleRepository.save(role);
    }

    @Override
    public void delete(final Long id) {
        roleRepository.deleteById(id);
    }

    private RoleDTO mapToDTO(final Role role, final RoleDTO roleDTO) {
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setDescription(role.getDescription());
        return roleDTO;
    }

    private Role mapToEntity(final RoleDTO roleDTO, final Role role) {
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());
        return role;
    }

    @Override
    public boolean nameExists(final String name) {
        return roleRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public String getReferencedWarning(final Long id) {
        final Role role = roleRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final User roleUser = userRepository.findFirstByRole(role);
        if (roleUser != null) {
            return WebUtils.getMessage("role.user.role.referenced", roleUser.getId());
        }
        return null;
    }

}
