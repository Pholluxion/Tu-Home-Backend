package com.phollux.tuhome.contract.service;

import com.phollux.tuhome.contract.domain.Contract;
import com.phollux.tuhome.contract.model.ContractDTO;
import com.phollux.tuhome.contract.repos.ContractRepository;
import com.phollux.tuhome.property.domain.Property;
import com.phollux.tuhome.property.repos.PropertyRepository;
import com.phollux.tuhome.user.domain.User;
import com.phollux.tuhome.user.repos.UserRepository;
import com.phollux.tuhome.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    public ContractServiceImpl(final ContractRepository contractRepository,
            final UserRepository userRepository, final PropertyRepository propertyRepository) {
        this.contractRepository = contractRepository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<ContractDTO> findAll() {
        final List<Contract> contracts = contractRepository.findAll(Sort.by("id"));
        return contracts.stream()
                .map(contract -> mapToDTO(contract, new ContractDTO()))
                .toList();
    }

    @Override
    public ContractDTO get(final Integer id) {
        return contractRepository.findById(id)
                .map(contract -> mapToDTO(contract, new ContractDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Integer create(final ContractDTO contractDTO) {
        final Contract contract = new Contract();
        mapToEntity(contractDTO, contract);
        return contractRepository.save(contract).getId();
    }

    @Override
    public void update(final Integer id, final ContractDTO contractDTO) {
        final Contract contract = contractRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(contractDTO, contract);
        contractRepository.save(contract);
    }

    @Override
    public void delete(final Integer id) {
        contractRepository.deleteById(id);
    }

    private ContractDTO mapToDTO(final Contract contract, final ContractDTO contractDTO) {
        contractDTO.setId(contract.getId());
        contractDTO.setStartDate(contract.getStartDate());
        contractDTO.setEndDate(contract.getEndDate());
        contractDTO.setRent(contract.getRent());
        contractDTO.setDeposit(contract.getDeposit());
        contractDTO.setStatus(contract.getStatus());
        contractDTO.setTenant(contract.getTenant() == null ? null : contract.getTenant().getId());
        contractDTO.setLandlord(contract.getLandlord() == null ? null : contract.getLandlord().getId());
        return contractDTO;
    }

    private Contract mapToEntity(final ContractDTO contractDTO, final Contract contract) {
        contract.setStartDate(contractDTO.getStartDate());
        contract.setEndDate(contractDTO.getEndDate());
        contract.setRent(contractDTO.getRent());
        contract.setDeposit(contractDTO.getDeposit());
        contract.setStatus(contractDTO.getStatus());
        final User tenant = contractDTO.getTenant() == null ? null : userRepository.findById(contractDTO.getTenant())
                .orElseThrow(() -> new NotFoundException("tenant not found"));
        contract.setTenant(tenant);
        final Property landlord = contractDTO.getLandlord() == null ? null : propertyRepository.findById(contractDTO.getLandlord())
                .orElseThrow(() -> new NotFoundException("landlord not found"));
        contract.setLandlord(landlord);
        return contract;
    }

}
