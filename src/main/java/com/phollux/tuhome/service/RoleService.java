package com.phollux.tuhome.service;

import com.phollux.tuhome.model.RoleDTO;
import java.util.List;


public interface RoleService {

    List<RoleDTO> findAll();

    RoleDTO get(Long id);

    Long create(RoleDTO roleDTO);

    void update(Long id, RoleDTO roleDTO);

    void delete(Long id);

    boolean nameExists(String name);

    String getReferencedWarning(Long id);

}
