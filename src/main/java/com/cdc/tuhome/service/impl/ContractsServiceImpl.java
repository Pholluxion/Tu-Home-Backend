package com.cdc.tuhome.service.impl;

import com.cdc.tuhome.dto.ContractsDTO;
import com.cdc.tuhome.mappers.ContractsMapper;
import com.cdc.tuhome.model.Contracts;
import com.cdc.tuhome.model.Properties;
import com.cdc.tuhome.model.Users;
import com.cdc.tuhome.repository.IContractsRepository;
import com.cdc.tuhome.service.interfaces.IContractsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdc.tuhome.mappers.PropertiesMapper;
import com.cdc.tuhome.mappers.UsersMapper;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractsServiceImpl implements IContractsService {
    
    private IContractsRepository contractsRepository;
    private PropertiesMapper propertiesMapper;
    private UsersMapper usersMapper;
    
    @Autowired
    public void setContractRepository (IContractsRepository contractsRepository) {
        this.contractsRepository = contractsRepository;
    }

    @Override
    public ContractsDTO createContract(ContractsDTO contract) {
        Contracts newContract = ContractsMapper.INSTANCE.toContracts(contract);
        newContract = contractsRepository.save(newContract);
        return ContractsMapper.INSTANCE.toContractsDTO(newContract);
    }
    
    @Override
    public List<ContractsDTO> getContracts() {
        List<Contracts> contracts = contractsRepository.findAll();
        return contracts.stream().map(ContractsMapper.INSTANCE::toContractsDTO).collect(Collectors.toList());
    }
    
    @Override
    public ContractsDTO getContractById(Long id) {
        Contracts contract = contractsRepository.findById((id)).orElse(null);
        return ContractsMapper.INSTANCE.toContractsDTO(contract);
    }

    @Override
    public ContractsDTO updateContract(Long id, ContractsDTO updatedContract) {
        Contracts existingContract = contractsRepository.findById(id).orElse(null);
        if (existingContract != null) {
            
            //Properties property = propertiesMapper.toProperties(updatedContract.getProperty());
            //Users user = usersMapper.toUsers(updatedContract.getUserId());
            
            existingContract.setAdmineamount(updatedContract.getAdmineamount());
            existingContract.setEnddate(updatedContract.getEnddate());
            existingContract.setIncludeadmin(updatedContract.getIncludeadmin());
            existingContract.setIncludeservices(updatedContract.getIncludeservices());
            
            //existingContract.setProperty(property);
            existingContract.setRentalamount(updatedContract.getRentalamount());
            existingContract.setServiceamount(updatedContract.getServiceamount());
            existingContract.setStartdate(updatedContract.getStartdate());
            //existingContract.setUserId(user);
            
            existingContract = contractsRepository.save(existingContract);
            
            return ContractsMapper.INSTANCE.toContractsDTO(existingContract);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteContract(Long id) {
        if (contractsRepository.existsById(id)) {
            contractsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }  
}
