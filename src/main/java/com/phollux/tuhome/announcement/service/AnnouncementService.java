package com.phollux.tuhome.announcement.service;

import com.phollux.tuhome.announcement.model.AnnouncementDTO;
import java.util.List;


public interface AnnouncementService {

    List<AnnouncementDTO> findAll();

    AnnouncementDTO get(Integer id);

    Integer create(AnnouncementDTO announcementDTO);

    void update(Integer id, AnnouncementDTO announcementDTO);

    void delete(Integer id);

}
