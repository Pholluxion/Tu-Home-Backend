package com.phollux.tuhome.notification.service;

import com.phollux.tuhome.notification.domain.Notification;
import com.phollux.tuhome.notification.model.NotificationDTO;
import com.phollux.tuhome.notification.repos.NotificationRepository;
import com.phollux.tuhome.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

public interface NotificationService {
    public List<NotificationDTO> findAll() ;
    public NotificationDTO get(final Long id) ;
    public Long create(final NotificationDTO notificationDTO) ;
    public void update(final Long id, final NotificationDTO notificationDTO) ;
    public void delete(final Long id);
}
