package com.danielnaiff.backend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionsHandler{

    @ExceptionHandler(AttributeDoenstExist.class)
    public ResponseEntity<RestErrorMessage> handlerCreateUser(AttributeDoenstExist exception){
        RestErrorMessage response = new RestErrorMessage(
                exception.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(TranferNotValidated.class)
    public ResponseEntity<RestErrorMessage> handlerTranferNotValidated(TranferNotValidated exception){
        RestErrorMessage response = new RestErrorMessage(
                exception.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(NotificationError.class)
    public ResponseEntity<RestErrorMessage> handlerNotificationError(NotificationError exception){
        RestErrorMessage response = new RestErrorMessage(
                exception.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
