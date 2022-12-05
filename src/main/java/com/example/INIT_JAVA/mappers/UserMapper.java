package com.example.INIT_JAVA.mappers;


import com.example.INIT_JAVA.DTOs.response.UserResponseDto;
import com.example.INIT_JAVA.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto mapToDto(User user);

}
