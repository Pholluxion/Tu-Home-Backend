package com.phollux.tuhome.rest;

import com.phollux.tuhome.model.PropertyDTO;
import com.phollux.tuhome.service.PropertyService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/properties", produces = MediaType.APPLICATION_JSON_VALUE)
public class PropertyResource {

    private final PropertyService propertyService;

    public PropertyResource(final PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        return ResponseEntity.ok(propertyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getProperty(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(propertyService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createProperty(
            @RequestBody @Valid final PropertyDTO propertyDTO) {
        final Integer createdId = propertyService.create(propertyDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateProperty(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final PropertyDTO propertyDTO) {
        propertyService.update(id, propertyDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteProperty(@PathVariable(name = "id") final Integer id) {
        propertyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
