package ru.ivanov.ecommerceplatformproject.requestmanagementservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.dto.request.BrandCreationRequestDto;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.enums.InitiatorType;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.enums.RequestStatus;

import java.util.UUID;

@Entity
@Table(name = "brand_creation_requests")
@Getter
@NoArgsConstructor
public final class BrandCreationRequest extends Request {

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "logo_url", updatable = false)
    private String logoUrl;

    @Column(name = "websiteUrl", updatable = false)
    private String websiteUrl;

    public BrandCreationRequest(UUID sellerId, BrandCreationRequestDto requestDto) {
        super(RequestStatus.SUBMITTED, InitiatorType.SELLER, sellerId);
        this.name = requestDto.name();
        this.logoUrl = requestDto.logoUrl();
        this.websiteUrl = requestDto.websiteUrl();
    }
}