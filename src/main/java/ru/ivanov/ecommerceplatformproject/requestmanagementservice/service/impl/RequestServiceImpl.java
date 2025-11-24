package ru.ivanov.ecommerceplatformproject.requestmanagementservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.dto.request.BrandCreationRequestDto;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.dto.request.ProductCreationRequestDto;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.event.RequestSubmittedEvent;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.BrandCreationRequest;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.OutboxEvent;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.ProductCreationRequest;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.enums.RequestType;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.repository.BrandCreationRequestRepository;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.repository.ProductCreationRequestRepository;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.service.OutboxEventService;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.service.RequestService;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final ProductCreationRequestRepository productCreationRequestRepository;
    private final BrandCreationRequestRepository brandCreationRequestRepository;
    private final OutboxEventService outboxEventService;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void processProductCreationRequest(UUID sellerId, ProductCreationRequestDto requestDto) {

        ProductCreationRequest request = new ProductCreationRequest(sellerId, requestDto);

        ProductCreationRequest savedRequest = productCreationRequestRepository.save(request);

        RequestSubmittedEvent event = new RequestSubmittedEvent(
                UUID.randomUUID(),
                savedRequest.getId(),
                RequestType.PRODUCT_CREATION,
                savedRequest.getInitiatorId(),
                Instant.now()
        );

        try {
            String outboxEventPayload = objectMapper.writeValueAsString(event);

            OutboxEvent outboxEvent = new OutboxEvent(
                    "product-requests",
                    event.requestType(),
                    event.requestId(),
                    "RequestSubmitted", //todo
                    outboxEventPayload
            );

            outboxEventService.save(outboxEvent);
        } catch (JacksonException e) {
            throw new RuntimeException("Failed to serialize event to outbox", e); //todo
        }
    }

    @Override
    @Transactional
    public void processBrandCreationRequest(UUID sellerId, BrandCreationRequestDto requestDto) {

        BrandCreationRequest request = new BrandCreationRequest(sellerId, requestDto);

        BrandCreationRequest savedRequest = brandCreationRequestRepository.save(request);

        RequestSubmittedEvent event = new RequestSubmittedEvent(
                UUID.randomUUID(),
                savedRequest.getId(),
                RequestType.BRAND_CREATION,
                savedRequest.getInitiatorId(),
                Instant.now()
        );

        try {
            String outboxEventPayload = objectMapper.writeValueAsString(event);

            OutboxEvent outboxEvent = new OutboxEvent(
                    "brand-requests",
                    event.requestType(),
                    event.requestId(),
                    "RequestSubmitted", //todo
                    outboxEventPayload
            );

            outboxEventService.save(outboxEvent);
        } catch (JacksonException e) {
            throw new RuntimeException("Failed to serialize event to outbox", e); //todo
        }
    }
}
