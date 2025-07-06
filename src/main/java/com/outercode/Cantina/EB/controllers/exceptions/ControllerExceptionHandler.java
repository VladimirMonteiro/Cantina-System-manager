package com.outercode.Cantina.EB.controllers.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Set<String> messages = new HashSet<>();

        // Coleta os erros de validação
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            messages.add(errorMessage); // Adiciona mensagem detalhada
        });

        // Cria o StandardError com o status e o Set de mensagens
        StandardError response = new StandardError(HttpStatus.BAD_REQUEST.value(), messages);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
