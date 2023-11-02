package com.phollux.tuhome.repos;

import com.phollux.tuhome.domain.Announcement;
import com.phollux.tuhome.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    Announcement findFirstByProperty(Property property);

}
