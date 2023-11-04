package com.phollux.tuhome.user.service;

import com.phollux.tuhome.user.model.UserDTO;
import java.util.List;


public interface UserService {

    List<UserDTO> findAll();

    UserDTO findByEmail(String email);

    UserDTO get(Long id);

    Long create(UserDTO userDTO);

    void update(Long id, UserDTO userDTO);

    void delete(Long id);

}
