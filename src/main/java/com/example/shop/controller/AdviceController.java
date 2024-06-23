package com.example.shop.controller;

import com.example.shop.model.dto.FieldErrorDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class AdviceController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        log.warn("Couldn't found entity in db", entityNotFoundException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<FieldErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.warn("Bad user data", methodArgumentNotValidException);

        return methodArgumentNotValidException.getAllErrors().stream()
                .map(o -> FieldErrorDto.builder()
                        .fieldName(((FieldError) o).getField())
                        .massage(o.getDefaultMessage())
                        .build())
                .toList();
    }
}
