package org.gameofclans.controllers;

import io.javalin.http.Context;
import org.gameofclans.service.AuditServiceImpl;

import java.io.IOException;

public class AuditController {
    private final AuditServiceImpl auditService;

    public AuditController(AuditServiceImpl auditService) {
        this.auditService = auditService;
    }

    public void get(Context context) {
        auditService.list().stream().forEach(m -> {
            try {
                context.outputStream().println(m.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
