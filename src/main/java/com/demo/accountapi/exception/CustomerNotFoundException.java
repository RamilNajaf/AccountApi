package com.demo.accountapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends  RuntimeException{

    public CustomerNotFoundException(String msg) {
        super(msg);
    }
}
