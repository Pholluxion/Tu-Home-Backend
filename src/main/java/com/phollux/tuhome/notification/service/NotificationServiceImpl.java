package com.phollux.tuhome.notification.service;

import com.phollux.tuhome.notification.domain.Notification;
import com.phollux.tuhome.notification.model.NotificationDTO;
import com.phollux.tuhome.notification.repos.NotificationRepository;
import com.phollux.tuhome.user.domain.User;
import com.phollux.tuhome.user.repos.UserRepository;
import com.phollux.tuhome.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImpl implements  NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationServiceImpl(final NotificationRepository notificationRepository, final UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<NotificationDTO> findAll() {
        final List<Notification> notifications = notificationRepository.findAll(Sort.by("id"));
        return notifications.stream()
                .map(notification -> mapToDTO(notification, new NotificationDTO()))
                .toList();
    }
    @Override
    public NotificationDTO get(final Long id) {
        return notificationRepository.findById(id)
                .map(notification -> mapToDTO(notification, new NotificationDTO()))
                .orElseThrow(NotFoundException::new);
    }
    @Override
    public Long create(final NotificationDTO notificationDTO) {
        final Notification notification = new Notification();
        mapToEntity(notificationDTO, notification);
        return notificationRepository.save(notification).getId();
    }
    @Override
    public void update(final Long id, final NotificationDTO notificationDTO) {
        final Notification notification = notificationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(notificationDTO, notification);
        notificationRepository.save(notification);
    }
    @Override
    public void delete(final Long id) {
        notificationRepository.deleteById(id);
    }

    private NotificationDTO mapToDTO(final Notification notification,
                                     final NotificationDTO notificationDTO) {
        notificationDTO.setId(notification.getId());
        notificationDTO.setDescription(notification.getDescription());
        notificationDTO.setTitle(notification.getTitle());
        notificationDTO.setUserId(notification.getUser() == null ? null : notification.getUser().getId());
        return notificationDTO;
    }

    private Notification mapToEntity(final NotificationDTO notificationDTO,
                                     final Notification notification) {
        notification.setDescription(notificationDTO.getDescription());
        notification.setTitle(notificationDTO.getTitle());
        final User tenant = notificationDTO.getUserId() == null ? null : userRepository.findById(notificationDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("user not found"));
        notification.setUser(tenant);

        return notification;
    }

}
