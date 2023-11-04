package com.phollux.tuhome.announcement.repos;

import com.phollux.tuhome.announcement.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
}
