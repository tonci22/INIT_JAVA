package com.example.INIT_JAVA.DTOs.request;

import lombok.Value;

import java.io.Serial;
import java.io.Serializable;


@Value
public class JwtRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 5926468583005150707L;

    String username;
    String password;
}
