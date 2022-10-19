package org.gameofclans.service;

import org.gameofclans.model.AuditMessage;
import org.gameofclans.repo.AuditRepository;

import java.util.List;

public class AuditServiceImpl implements AuditService {
    private final AuditRepository repo;

    public AuditServiceImpl(AuditRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<AuditMessage> list() {
        return repo.list();
    }

    @Override
    public void add(String msg, Object... args) {
        repo.add(String.format(msg, args));
    }
}
