package com.phollux.tuhome.service;

import com.phollux.tuhome.model.UserDTO;
import java.util.List;


public interface UserService {

    List<UserDTO> findAll();

    UserDTO get(Long id);

    Long create(UserDTO userDTO);

    void update(Long id, UserDTO userDTO);

    void delete(Long id);

    String getReferencedWarning(Long id);

}
