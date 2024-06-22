package com.example.shop.mapper;

import com.example.shop.model.dao.User;
import com.example.shop.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto map(User user);

    User map(UserDto userDto);


}
