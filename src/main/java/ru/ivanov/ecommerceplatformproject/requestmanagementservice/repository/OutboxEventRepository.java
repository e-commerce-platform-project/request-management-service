package ru.ivanov.ecommerceplatformproject.requestmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.OutboxEvent;

import java.util.UUID;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, UUID> {
}