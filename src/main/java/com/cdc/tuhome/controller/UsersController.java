package com.cdc.tuhome.controller;

import com.cdc.tuhome.dto.UsersDTO;
import com.cdc.tuhome.service.interfaces.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {
    private IUsersService userService;
    
    @Autowired
    public void setUsersService(IUsersService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO userDTO) {
        UsersDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
    @GetMapping
    public ResponseEntity<List<UsersDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable Long id) {
        UsersDTO usersDTO = this.userService.getUserById(id);
        return ResponseEntity.ok(usersDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable Long id, @RequestBody UsersDTO user) {
        UsersDTO updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deteleUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
