package com.example.shop.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDto(@NotBlank String name, String description, @NotBlank BigDecimal price, @NotBlank int quantity) {
}
