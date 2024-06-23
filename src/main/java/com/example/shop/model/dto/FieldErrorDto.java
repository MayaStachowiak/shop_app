package com.example.shop.model.dto;

import lombok.Builder;

@Builder
public record FieldErrorDto(String fieldName, String massage) {
}