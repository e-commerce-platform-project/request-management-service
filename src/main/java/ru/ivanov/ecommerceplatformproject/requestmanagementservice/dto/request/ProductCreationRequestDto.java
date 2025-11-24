package ru.ivanov.ecommerceplatformproject.requestmanagementservice.dto.request;


import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ProductCreationRequestDto(
        @NotNull
        String name,

        @NotNull
        String description,

        @NotNull
        BigDecimal basePrice,

        @NotNull
        UUID categoryId,

        UUID brandId,

        @NotNull
        String mainImageUrl,

        List<String> additionalImageUrls
) {
}