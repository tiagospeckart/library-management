package br.com.tiagospeckart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Login not found")
public class LoginNotFoundException extends RuntimeException{

    public LoginNotFoundException(){
        super("Login not found");
    }
}
