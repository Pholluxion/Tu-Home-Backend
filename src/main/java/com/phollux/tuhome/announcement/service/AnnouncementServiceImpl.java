package com.phollux.tuhome.announcement.service;

import com.phollux.tuhome.announcement.domain.Announcement;
import com.phollux.tuhome.announcement.model.AnnouncementDTO;
import com.phollux.tuhome.announcement.repos.AnnouncementRepository;
import com.phollux.tuhome.property.domain.Property;
import com.phollux.tuhome.property.repos.PropertyRepository;
import com.phollux.tuhome.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final PropertyRepository propertyRepository;

    public AnnouncementServiceImpl(final AnnouncementRepository announcementRepository,
            final PropertyRepository propertyRepository) {
        this.announcementRepository = announcementRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<AnnouncementDTO> findAll() {
        final List<Announcement> announcements = announcementRepository.findAll(Sort.by("id"));
        return announcements.stream()
                .map(announcement -> mapToDTO(announcement, new AnnouncementDTO()))
                .toList();
    }

    @Override
    public AnnouncementDTO get(final Integer id) {
        return announcementRepository.findById(id)
                .map(announcement -> mapToDTO(announcement, new AnnouncementDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Integer create(final AnnouncementDTO announcementDTO) {
        final Announcement announcement = new Announcement();
        mapToEntity(announcementDTO, announcement);
        return announcementRepository.save(announcement).getId();
    }

    @Override
    public void update(final Integer id, final AnnouncementDTO announcementDTO) {
        final Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(announcementDTO, announcement);
        announcementRepository.save(announcement);
    }

    @Override
    public void delete(final Integer id) {
        announcementRepository.deleteById(id);
    }

    private AnnouncementDTO mapToDTO(final Announcement announcement,
            final AnnouncementDTO announcementDTO) {
        announcementDTO.setId(announcement.getId());
        announcementDTO.setTitle(announcement.getTitle());
        announcementDTO.setDescription(announcement.getDescription());
        announcementDTO.setPrice(announcement.getPrice());
        announcementDTO.setAddress(announcement.getAddress());
        announcementDTO.setBedrooms(announcement.getBedrooms());
        announcementDTO.setBathrooms(announcement.getBathrooms());
        announcementDTO.setSquareFeet(announcement.getSquareFeet());
        announcementDTO.setPhotos(announcement.getPhotos());
        announcementDTO.setProperty(announcement.getProperty() == null ? null : announcement.getProperty().getId());
        return announcementDTO;
    }

    private Announcement mapToEntity(final AnnouncementDTO announcementDTO,
            final Announcement announcement) {
        announcement.setTitle(announcementDTO.getTitle());
        announcement.setDescription(announcementDTO.getDescription());
        announcement.setPrice(announcementDTO.getPrice());
        announcement.setAddress(announcementDTO.getAddress());
        announcement.setBedrooms(announcementDTO.getBedrooms());
        announcement.setBathrooms(announcementDTO.getBathrooms());
        announcement.setSquareFeet(announcementDTO.getSquareFeet());
        announcement.setPhotos(announcementDTO.getPhotos());
        final Property property = announcementDTO.getProperty() == null ? null : propertyRepository.findById(announcementDTO.getProperty())
                .orElseThrow(() -> new NotFoundException("property not found"));
        announcement.setProperty(property);
        return announcement;
    }

}
