package com.cdc.tuhome.controller;

import com.cdc.tuhome.dto.ContractsDTO;
import com.cdc.tuhome.service.interfaces.IContractsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractsController {
    
    private IContractsService contractService;
    
    @Autowired
    public void setContractsService(IContractsService contractService) {
        this.contractService = contractService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<ContractsDTO> createContract(@RequestBody ContractsDTO contractDTO) {
        ContractsDTO createdContract = contractService.createContract(contractDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContract);
    }
    
    @GetMapping
    public ResponseEntity<List<ContractsDTO>> getContracts() {
        return ResponseEntity.ok(contractService.getContracts());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ContractsDTO> getContractById(@PathVariable Long id) {
        ContractsDTO contractDTO = this.contractService.getContractById(id);
        return ResponseEntity.ok(contractDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ContractsDTO> updateContract(@PathVariable Long id, @RequestBody ContractsDTO contractDTO) {
        ContractsDTO updatedContract = contractService.updateContract(id, contractDTO);
        if (updatedContract != null) {
            return ResponseEntity.ok(updatedContract);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.deleteContract(id));
    }
}
