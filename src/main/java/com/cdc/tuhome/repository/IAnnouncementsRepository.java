package com.cdc.tuhome.repository;

import com.cdc.tuhome.model.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnnouncementsRepository extends JpaRepository<Announcements, Long> {
}
