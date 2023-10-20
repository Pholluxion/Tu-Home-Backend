package com.cdc.tuhome.mappers;

import com.cdc.tuhome.dto.ContractsDTO;
import com.cdc.tuhome.model.Contracts;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContractsMapper {
    ContractsMapper INSTANCE = Mappers.getMapper(ContractsMapper.class);

    ContractsDTO toContractsDTO(Contracts contracts);

    Contracts toContracts(ContractsDTO contractsDTO);
}
