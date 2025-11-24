package ru.ivanov.ecommerceplatformproject.requestmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.BrandCreationRequest;

import java.util.UUID;

public interface BrandCreationRequestRepository extends JpaRepository<BrandCreationRequest, UUID> {
}