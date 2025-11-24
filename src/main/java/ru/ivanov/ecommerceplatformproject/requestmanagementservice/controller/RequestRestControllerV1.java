package ru.ivanov.ecommerceplatformproject.requestmanagementservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.dto.request.BrandCreationRequestDto;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.dto.request.ProductCreationRequestDto;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.service.RequestService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/requests")
@RequiredArgsConstructor
public class RequestRestControllerV1 {

    private final RequestService requestService;


    @PostMapping("/products/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void processProductCreationRequest(
            @RequestBody @Valid ProductCreationRequestDto requestDto,
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID sellerId = UUID.fromString(jwt.getSubject());
        requestService.processProductCreationRequest(sellerId, requestDto);
    }

    @PostMapping("/brands/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void processBrandCreationRequest(
            @RequestBody @Valid BrandCreationRequestDto requestDto,
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID sellerId = UUID.fromString(jwt.getSubject());
        requestService.processBrandCreationRequest(sellerId, requestDto);
    }
}