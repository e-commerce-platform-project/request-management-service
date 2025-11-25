package ru.ivanov.ecommerceplatformproject.requestmanagementservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.dto.request.ProductCreationRequestDto;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.enums.InitiatorType;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.enums.RequestStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product_creation_requests")
@Getter
@NoArgsConstructor
public final class ProductCreationRequest extends Request {

    @Column(name = "name", nullable = false, updatable = false) // todo в будущем можно будет добавить возможность изменения
    private String name;                                        // todo данных заявки

    @Column(name = "description", nullable = false, updatable = false)
    private String description;

    @Column(name = "base_price", nullable = false, updatable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "category_id", nullable = false, updatable = false)
    private UUID categoryId;

    @Column(name = "brand_id", updatable = false)
    private UUID brandId;

    @Column(name = "main_image_url", nullable = false, updatable = false)
    private String mainImageUrl;

    @ElementCollection
    @CollectionTable(
            name = "product_creation_request_additional_images",
            joinColumns = @JoinColumn(name = "product_creation_request_id")
    )
    @Column(name = "image_url")
    private List<String> additionalImageUrls;

    public ProductCreationRequest(UUID sellerId, ProductCreationRequestDto requestDto) {

        super(RequestStatus.SUBMITTED, InitiatorType.SELLER, sellerId);

        this.name = requestDto.name();
        this.description = requestDto.description();
        this.basePrice = requestDto.basePrice();
        this.categoryId = requestDto.categoryId();
        this.brandId = requestDto.brandId();
        this.mainImageUrl = requestDto.mainImageUrl();
        this.additionalImageUrls = requestDto.additionalImageUrls();
    }
}