package ru.ivanov.ecommerceplatformproject.requestmanagementservice.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.service.OutboxEventProcessor;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxScheduler {

    private final OutboxEventProcessor outboxEventProcessor;

    @Scheduled(fixedDelay = 5000L)
    public void processOutboxEvents() {
        try {
            log.debug("Starting outbox events processing");
            outboxEventProcessor.processPendingEvents();
        } catch (Exception e) {
            log.error("Error in outbox scheduler: {}", e.getMessage(), e);
        }
    }
}