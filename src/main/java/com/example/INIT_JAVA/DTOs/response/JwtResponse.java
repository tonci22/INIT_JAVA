package com.example.INIT_JAVA.DTOs.response;

import lombok.Value;

import java.io.Serial;
import java.io.Serializable;

@Value
public class JwtResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -8091879091924046844L;
    String token;

}