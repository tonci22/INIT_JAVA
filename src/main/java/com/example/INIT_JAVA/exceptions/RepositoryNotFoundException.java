package com.example.INIT_JAVA.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RepositoryNotFoundException extends RuntimeException {

    public RepositoryNotFoundException() {
        super();
    }

    public RepositoryNotFoundException(String message) {
        super(message);
    }

    public RepositoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

