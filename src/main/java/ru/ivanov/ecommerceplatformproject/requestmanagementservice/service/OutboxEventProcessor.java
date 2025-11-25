package ru.ivanov.ecommerceplatformproject.requestmanagementservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.OutboxEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxEventProcessor {

    private final OutboxEventService outboxEventService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public void processPendingEvents() {

        List<OutboxEvent> pendingEvents = outboxEventService.findPendingService();

        if (pendingEvents.isEmpty()) {
            return;
        }

        for (OutboxEvent event : pendingEvents) {
            try {
                processEvent(event);
            } catch (Exception e) {
                log.error("Failed to process outbox event {}: {}", event.getId(), e.getMessage());
            }
        }
    }

    private void processEvent(OutboxEvent event) {

        String kafkaKey = event.getRequestId().toString();

        CompletableFuture<SendResult<String, Object>> futureResult = kafkaTemplate.send(
                event.getTopic(),
                kafkaKey,
                event
        );


        futureResult.whenComplete((result, throwable) -> {
            if (throwable == null) {
                handleSuccess(event, result);
            } else {
                handleFailure(event, throwable);
            }
        });
    }

    private void handleSuccess(OutboxEvent event, SendResult<String, Object> result) {
        try {
            event.markAsSent();
            outboxEventService.save(event);
            log.info("Successfully sent event {} to topic {} partition {} offset {}",
                    event.getId(),
                    result.getRecordMetadata().topic(),
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset()
            );
        } catch (Exception e) {
            log.error("Failed to mark event {} as sent: {}", event.getId(), e.getMessage());
        }
    }

    private void handleFailure(OutboxEvent event, Throwable throwable) {
        log.error("Failed to send event {} to Kafka: {}", event.getId(), throwable.getMessage());
        // Можно добавить логику для повторных попыток, например:
        // - Счетчик попыток
        // - Экспоненциальная backoff-стратегия
        // - Помещение в DLQ после N попыток
    }
}