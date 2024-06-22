package com.example.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        @NotBlank
//        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\\$%\\^&\\*])(?=.{8,})",
//        message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, "
//        + "one special character, and be at least 8 characters long")
        String password,
        String confirmPassword,
        Long id,
        @NotBlank String userName,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email String email,
        @Length(min = 9, max = 9) String phoneNumber) {
}
