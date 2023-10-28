package com.example.productmanagement.exception;

import com.example.productmanagement.exceptionModel.ExceptionBody;
import com.example.productmanagement.exceptionModel.FieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handle(BindException e) {
        List<String> globalErrors = Optional.of(e)
                .map(BindException::getGlobalErrors)
                .stream()
                .flatMap(Collection::stream)
                .map(ObjectError::getDefaultMessage)
                .toList();

        List<FieldError> fieldErrors = e.getFieldErrors();
        List<FieldException> errors = fieldErrors.stream()
                .map(error -> new FieldException(error.getField(),
                        error.getDefaultMessage()))
                .toList();
        ExceptionBody body = new ExceptionBody(globalErrors, errors);


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
