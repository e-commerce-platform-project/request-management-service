package ru.ivanov.ecommerceplatformproject.requestmanagementservice.service;

import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.OutboxEvent;

public interface OutboxEventService {
    void save(OutboxEvent outboxEvent);
}