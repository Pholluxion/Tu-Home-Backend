package com.cdc.tuhome.mappers;

import com.cdc.tuhome.dto.UsersDTO;
import com.cdc.tuhome.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersMapper {
    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    UsersDTO toUsersDTO(Users users);

    Users toUsers(UsersDTO usersDTO);

}
