package com.phollux.tuhome.notification.repos;

import com.phollux.tuhome.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
