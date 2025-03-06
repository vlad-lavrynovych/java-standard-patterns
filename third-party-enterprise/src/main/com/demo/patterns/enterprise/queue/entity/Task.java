package com.demo.patterns.enterprise.queue.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    private String entityType;  // e.g., "Customer", "Product"
    private Long entityId;      // ID of the target entity

    @Lob
    private String payload;     // JSON payload for flexibility

    private String status;

    public enum TaskType {
        CHANGE_STATE,
        UPDATE_DETAILS,
        NOTIFY_CLIENT
    }
}
