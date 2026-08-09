package br.com.github.renato28.javaspringsecurity.infra.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class TratadorDeException {

    @ExceptionHandler(NoSuchElementException.class)
    public String tratarErro404() {
        return "erro/404";
    }

    @ExceptionHandler(Exception.class)
    public String tratarErro500() {
        return "erro/500";
    }
}
