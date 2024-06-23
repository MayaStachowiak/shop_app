package com.example.shop.validator.impl;

import com.example.shop.model.dto.UserDto;
import com.example.shop.validator.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidatorImpl implements ConstraintValidator<PasswordValidator, UserDto> {

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.password().equals(userDto.confirmPassword());
    }
}