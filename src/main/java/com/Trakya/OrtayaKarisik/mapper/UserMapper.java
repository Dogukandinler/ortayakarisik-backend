package com.Trakya.OrtayaKarisik.mapper;

import com.Trakya.OrtayaKarisik.model.User;
import com.Trakya.OrtayaKarisik.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserDto toDto(User user);
}
