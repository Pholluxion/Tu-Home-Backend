package com.phollux.tuhome.contract.service;

import com.phollux.tuhome.contract.model.ContractDTO;
import java.util.List;


public interface ContractService {

    List<ContractDTO> findAll();

    ContractDTO get(Integer id);

    Integer create(ContractDTO contractDTO);

    void update(Integer id, ContractDTO contractDTO);

    void delete(Integer id);

}
