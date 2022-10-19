package org.gameofclans.service;

import org.gameofclans.model.AuditMessage;

import java.util.List;

public interface AuditService {
    List<AuditMessage> list();

    void add(String msg, Object... args);
}
