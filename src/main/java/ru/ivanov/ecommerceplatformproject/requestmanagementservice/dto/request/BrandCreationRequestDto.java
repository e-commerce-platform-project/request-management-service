package ru.ivanov.ecommerceplatformproject.requestmanagementservice.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BrandCreationRequestDto(
        @NotBlank
        String name,

        String logoUrl,

        String website
) {
}