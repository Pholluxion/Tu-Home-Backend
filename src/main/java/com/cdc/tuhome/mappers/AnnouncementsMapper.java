package com.cdc.tuhome.mappers;

import com.cdc.tuhome.dto.AnnouncementsDTO;
import com.cdc.tuhome.model.Announcements;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnnouncementsMapper {
    AnnouncementsMapper INSTANCE = Mappers.getMapper(AnnouncementsMapper.class);

    AnnouncementsDTO toAnnouncementsDTO(Announcements announcements);

    Announcements toAnnouncements(AnnouncementsDTO announcementsDTO);
}
