package ru.ivanov.ecommerceplatformproject.requestmanagementservice.service;

import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.OutboxEvent;

import java.util.List;

public interface OutboxEventService {
    void save(OutboxEvent outboxEvent);

    List<OutboxEvent> findPendingService();
}