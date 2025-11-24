package ru.ivanov.ecommerceplatformproject.requestmanagementservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.OutboxEvent;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.repository.OutboxEventRepository;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.service.OutboxEventService;

@Service
@RequiredArgsConstructor
public class OutboxEventServiceImpl implements OutboxEventService {

    private final OutboxEventRepository outboxEventRepository;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void save(OutboxEvent outboxEvent) {
        outboxEventRepository.save(outboxEvent);
    }
}