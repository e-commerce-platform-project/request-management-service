package ru.ivanov.ecommerceplatformproject.requestmanagementservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.enums.OutboxEventStatus;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.enums.RequestType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox")
@Getter
@NoArgsConstructor
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type", nullable = false)
    private RequestType requestType;

    @Column(name = "request_id", nullable = false)
    private UUID requestId;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payload", nullable = false)
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OutboxEventStatus status = OutboxEventStatus.PENDING;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;


    public OutboxEvent(String topic, RequestType requestType, UUID requestId, String eventType, String payload) {
        this.topic = topic;
        this.requestType = requestType;
        this.requestId = requestId;
        this.eventType = eventType;
        this.payload = payload;
    }

    public void markAsSent() {
        this.status = OutboxEventStatus.SENT;
    }
}