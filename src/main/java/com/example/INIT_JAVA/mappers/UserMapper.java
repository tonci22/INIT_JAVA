package com.example.INIT_JAVA.mappers;


import com.example.INIT_JAVA.DTOs.request.UserRequestDto;
import com.example.INIT_JAVA.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapToDto(UserRequestDto userRequestDto);
}
