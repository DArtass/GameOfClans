package org.gameofclans.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditMessage {
    private final long id;
    private final String message;
    private final LocalDateTime timestamp;
}
