package com.example.bb.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExists extends RuntimeException {

    public UserExists(String message) {
        super(message);
    }

}
