package com.phollux.tuhome.rest;

import com.phollux.tuhome.model.AnnouncementDTO;
import com.phollux.tuhome.service.AnnouncementService;
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
@RequestMapping(value = "/api/announcements", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnnouncementResource {

    private final AnnouncementService announcementService;

    public AnnouncementResource(final AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementDTO>> getAllAnnouncements() {
        return ResponseEntity.ok(announcementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDTO> getAnnouncement(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(announcementService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createAnnouncement(
            @RequestBody @Valid final AnnouncementDTO announcementDTO) {
        final Integer createdId = announcementService.create(announcementDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateAnnouncement(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final AnnouncementDTO announcementDTO) {
        announcementService.update(id, announcementDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable(name = "id") final Integer id) {
        announcementService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
