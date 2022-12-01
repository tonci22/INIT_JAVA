package com.example.INIT_JAVA.mappers;

import com.example.INIT_JAVA.DTOs.response.RoleResponseDto;
import com.example.INIT_JAVA.domain.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponseDto mapToDto(Role role);
}
