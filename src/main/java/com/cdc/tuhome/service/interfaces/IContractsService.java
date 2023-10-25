package com.cdc.tuhome.service.interfaces;

import com.cdc.tuhome.dto.ContractsDTO;
import java.util.List;

public interface IContractsService {
    
    ContractsDTO createContract(ContractsDTO contractDTO);
    
    List<ContractsDTO> getContracts();
    
    ContractsDTO getContractById(Long id);
    
    ContractsDTO updateContract(Long id, ContractsDTO contract);
    
    Boolean deleteContract(Long id);
}
