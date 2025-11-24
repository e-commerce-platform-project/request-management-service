package ru.ivanov.ecommerceplatformproject.requestmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.ProductCreationRequest;

import java.util.UUID;

public interface ProductCreationRequestRepository extends JpaRepository<ProductCreationRequest, UUID> {
}