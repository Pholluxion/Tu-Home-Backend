package com.cdc.tuhome.service.interfaces;

import com.cdc.tuhome.dto.UsersDTO;
import java.util.List;

public interface IUsersService {
    
    UsersDTO createUser(UsersDTO user);
    
    List<UsersDTO> getUsers();
    
    UsersDTO getUserById(Long id);
    
    UsersDTO updateUser(Long id, UsersDTO user);
    
    Boolean deleteUser(Long id);
}
