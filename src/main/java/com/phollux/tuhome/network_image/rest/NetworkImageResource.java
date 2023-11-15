package com.phollux.tuhome.network_image.rest;

import com.phollux.tuhome.model.SimplePage;
import com.phollux.tuhome.network_image.model.NetworkImageDTO;
import com.phollux.tuhome.network_image.service.NetworkImageService;
import com.phollux.tuhome.util.UserRoles;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/networkImages", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "')")
@SecurityRequirement(name = "bearer-jwt")
public class NetworkImageResource {

    private final NetworkImageService networkImageService;

    public NetworkImageResource(final NetworkImageService networkImageService) {
        this.networkImageService = networkImageService;
    }

    @Operation(
            parameters = {
                    @Parameter(
                            name = "page",
                            in = ParameterIn.QUERY,
                            schema = @Schema(implementation = Integer.class)
                    ),
                    @Parameter(
                            name = "size",
                            in = ParameterIn.QUERY,
                            schema = @Schema(implementation = Integer.class)
                    ),
                    @Parameter(
                            name = "sort",
                            in = ParameterIn.QUERY,
                            schema = @Schema(implementation = String.class)
                    )
            }
    )
    @GetMapping
    public ResponseEntity<SimplePage<NetworkImageDTO>> getAllNetworkImages(
            @RequestParam(required = false, name = "filter") final String filter,
            @Parameter(hidden = true) @SortDefault(sort = "id") @PageableDefault(size = 20) final Pageable pageable) {
        return ResponseEntity.ok(networkImageService.findAll(filter, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NetworkImageDTO> getNetworkImage(
            @PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(networkImageService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createNetworkImage(
            @RequestBody @Valid final NetworkImageDTO networkImageDTO) {
        final UUID createdId = networkImageService.create(networkImageDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateNetworkImage(@PathVariable(name = "id") final UUID id,
                                                   @RequestBody @Valid final NetworkImageDTO networkImageDTO) {
        networkImageService.update(id, networkImageDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteNetworkImage(@PathVariable(name = "id") final UUID id) {
        networkImageService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
