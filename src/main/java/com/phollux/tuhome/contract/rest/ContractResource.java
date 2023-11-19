package com.phollux.tuhome.contract.rest;

import com.phollux.tuhome.contract.model.ContractDTO;
import com.phollux.tuhome.contract.service.ContractService;
import com.phollux.tuhome.util.UserRoles;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/contracts", produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "bearer-jwt")
public class ContractResource {

    private final ContractService contractService;

    public ContractResource(final ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "')")
    public ResponseEntity<List<ContractDTO>> getAllContracts() {
        return ResponseEntity.ok(contractService.findAll());
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "') or hasAuthority('" + UserRoles.USER + "')")
    public ResponseEntity<List<ContractDTO>> getAllContractsByUserId(@PathVariable(name = "userId") final Long tenantId) {
        return ResponseEntity.ok(contractService.findByUserId(tenantId));
    }

    @GetMapping("/property/{propertyId}")
    @PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "') or hasAuthority('" + UserRoles.USER + "')")
    public ResponseEntity<List<ContractDTO>> getAllContractsByPropertyId(@PathVariable(name = "propertyId") final Integer landlordId) {
        return ResponseEntity.ok(contractService.findByPropertyId(landlordId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "')")
    public ResponseEntity<ContractDTO> getContract(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(contractService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    @PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "')")
    public ResponseEntity<Integer> createContract(
            @RequestBody @Valid final ContractDTO contractDTO) {
        final Integer createdId = contractService.create(contractDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "')")
    public ResponseEntity<Integer> updateContract(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final ContractDTO contractDTO) {
        contractService.update(id, contractDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    @PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "')")
    public ResponseEntity<Void> deleteContract(@PathVariable(name = "id") final Integer id) {
        contractService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
