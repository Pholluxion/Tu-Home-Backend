package com.cdc.tuhome.service.impl;

import com.cdc.tuhome.dto.UsersDTO;
import com.cdc.tuhome.mappers.UsersMapper;
import com.cdc.tuhome.model.Users;
import com.cdc.tuhome.repository.IUsersRepository;
import com.cdc.tuhome.service.interfaces.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements IUsersService {
    private IUsersRepository usersRepository;
    
    @Autowired
    public void setUserRepository (IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    
    @Override
    public UsersDTO createUser(UsersDTO user) {
        Users newUser = UsersMapper.INSTANCE.toUsers(user);
        newUser = usersRepository.save(newUser);
        return UsersMapper.INSTANCE.toUsersDTO(newUser);
    }
    
    @Override
    public List<UsersDTO> getUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream().map(UsersMapper.INSTANCE::toUsersDTO).collect(Collectors.toList());
    }
    
    @Override
    public UsersDTO getUserById(Long id) {
        Users user = usersRepository.findById((id)).orElse(null);
        return UsersMapper.INSTANCE.toUsersDTO(user);
    }
    
    @Override
    public UsersDTO updateUser(Long id, UsersDTO updatedUser) {
        Users existingUser = usersRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setFirstname(updatedUser.getFirstname());
            existingUser.setLastname(updatedUser.getLastname());
            existingUser.setIdcard(updatedUser.getIdcard());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setPhone(updatedUser.getPhone());
            
            existingUser = usersRepository.save(existingUser);
            
            return UsersMapper.INSTANCE.toUsersDTO(existingUser);
        } else {
            return null;
        }
    }
    
    @Override
    public Boolean deleteUser(Long id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
