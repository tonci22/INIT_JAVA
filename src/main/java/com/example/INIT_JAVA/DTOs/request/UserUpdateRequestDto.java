package com.example.INIT_JAVA.DTOs.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequestDto {

    String name;
    String password;
    boolean enabled;
    boolean tokenExpired;
}
