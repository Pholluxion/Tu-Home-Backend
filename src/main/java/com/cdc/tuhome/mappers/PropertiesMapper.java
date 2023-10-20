package com.cdc.tuhome.mappers;

import com.cdc.tuhome.dto.PropertiesDTO;
import com.cdc.tuhome.model.Properties;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PropertiesMapper {
    PropertiesMapper INSTANCE = Mappers.getMapper(PropertiesMapper.class);

    PropertiesDTO toPropertiesDTO(Properties properties);

    Properties toProperties(PropertiesDTO propertiesDTO);
}
