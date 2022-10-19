package org.gameofclans.service;

import org.gameofclans.repo.AuditRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AuditServiceTest {

    private AuditServiceImpl audit;

    @Test
    void add() {
        AuditRepository dbMock = mock(AuditRepository.class);

        audit = new AuditServiceImpl(dbMock);
        audit.add("Add gold %d", 10);

        verify(dbMock).add("Add gold 10");
    }
}