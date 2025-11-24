package ru.ivanov.ecommerceplatformproject.requestmanagementservice.service;

import ru.ivanov.ecommerceplatformproject.requestmanagementservice.dto.request.BrandCreationRequestDto;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.dto.request.ProductCreationRequestDto;

import java.util.UUID;

public interface RequestService {

    void processProductCreationRequest(UUID sellerId, ProductCreationRequestDto requestDto);

    void processBrandCreationRequest(UUID sellerId, BrandCreationRequestDto requestDto);
}