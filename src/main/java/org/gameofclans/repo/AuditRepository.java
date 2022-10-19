package org.gameofclans.repo;

import org.gameofclans.model.AuditMessage;

import java.util.List;

public interface AuditRepository {
    List<AuditMessage> list();

    void add(String info);
}
