package ru.ivanov.ecommerceplatformproject.requestmanagementservice.event;

import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.enums.RequestType;

import java.time.Instant;
import java.util.UUID;

public record RequestSubmittedEvent(
        UUID eventId,
        UUID requestId,
        RequestType requestType,
        UUID initiatorId,
        Instant occurredAt
) {
}