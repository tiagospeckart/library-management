package br.com.tiagospeckart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Login is not the same as configured")
public class WrongLoginException extends RuntimeException {

    public WrongLoginException(){
        super("Login is not the same as configured");
    }
}
