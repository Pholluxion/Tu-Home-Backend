package com.phollux.tuhome.announcement.rest;

import com.phollux.tuhome.announcement.model.AnnouncementDTO;
import com.phollux.tuhome.announcement.service.AnnouncementService;
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
@RequestMapping(value = "/api/announcements", produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "bearer-jwt")
public class AnnouncementResource {

    private final AnnouncementService announcementService;

    public AnnouncementResource(final AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PreAuthorize("hasAuthority('" + UserRoles.USER + "') or hasAuthority('" + UserRoles.ADMIN + "')")
    @GetMapping
    public ResponseEntity<List<AnnouncementDTO>> getAllAnnouncements() {
        return ResponseEntity.ok(announcementService.findAll());
    }
    @PreAuthorize("hasAuthority('" + UserRoles.USER + "') or hasAuthority('" + UserRoles.ADMIN + "')")
    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDTO> getAnnouncement(
            @PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(announcementService.get(id));
    }

    @PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "')")
    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createAnnouncement(
            @RequestBody @Valid final AnnouncementDTO announcementDTO) {
        final Integer createdId = announcementService.create(announcementDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "')")
    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateAnnouncement(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final AnnouncementDTO announcementDTO) {
        announcementService.update(id, announcementDTO);
        return ResponseEntity.ok(id);
    }

    @PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "')")
    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable(name = "id") final Integer id) {
        announcementService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
