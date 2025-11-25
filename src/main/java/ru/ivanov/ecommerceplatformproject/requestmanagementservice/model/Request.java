package ru.ivanov.ecommerceplatformproject.requestmanagementservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.enums.InitiatorType;
import ru.ivanov.ecommerceplatformproject.requestmanagementservice.model.enums.RequestStatus;
import ru.ivanov.ecommerceplatformproject.sharedlibs.enums.RequestType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@NoArgsConstructor
public abstract class Request {

    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @Column(name = "type", nullable = false, updatable = false)
//    @Enumerated(EnumType.STRING)
//    private RequestType type;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Column(name = "initiator_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private InitiatorType initiatorType;

    @Column(name = "initiator_id", nullable = false)
    private UUID initiatorId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected Request(RequestStatus status,  InitiatorType initiatorType, UUID initiatorId) {
        this.status = status;
        this.initiatorType = initiatorType;
        this.initiatorId = initiatorId;
    }
}