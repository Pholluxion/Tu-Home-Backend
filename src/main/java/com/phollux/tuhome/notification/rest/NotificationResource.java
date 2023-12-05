package com.phollux.tuhome.notification.rest;

import com.phollux.tuhome.notification.model.NotificationDTO;
import com.phollux.tuhome.notification.service.NotificationServiceImpl;
import com.phollux.tuhome.util.UserRoles;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping(value = "/api/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificationResource {

    private final NotificationServiceImpl notificationService;

    public NotificationResource(final NotificationServiceImpl notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + UserRoles.USER + "') or hasAuthority('" + UserRoles.ADMIN + "')")
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + UserRoles.USER + "') or hasAuthority('" + UserRoles.ADMIN + "')")
    public ResponseEntity<NotificationDTO> getNotification(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(notificationService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    @PreAuthorize("hasAuthority('" + UserRoles.USER + "') or hasAuthority('" + UserRoles.ADMIN + "')")
    public ResponseEntity<Long> createNotification(
            @RequestBody @Valid final NotificationDTO notificationDTO) {
        final Long createdId = notificationService.create(notificationDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "')")
    public ResponseEntity<Long> updateNotification(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final NotificationDTO notificationDTO) {
        notificationService.update(id, notificationDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    @PreAuthorize("hasAuthority('" + UserRoles.ADMIN + "')")
    public ResponseEntity<Void> deleteNotification(@PathVariable(name = "id") final Long id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
