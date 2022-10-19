package org.gameofclans.repo;

import org.gameofclans.model.AuditMessage;
import org.gameofclans.model.Clan;
import org.hsqldb.jdbc.JDBCPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DbAuditRepositoryTest extends RepoBaseTest{
    private DbAuditRepository dbAuditRepository;
    @BeforeEach
    void setup() throws Exception {
        setupPool();
        dbAuditRepository = new DbAuditRepository(pool);
    }

    @Test
    void addAndList() {
        dbAuditRepository.add("govn1");
        dbAuditRepository.add("govn2");
        List<AuditMessage> actual = dbAuditRepository.list();
        assertEquals(2, actual.size());
        assertEquals(1, actual.get(0).getId());
        assertEquals("govn1", actual.get(0).getMessage());

        assertEquals(2, actual.get(1).getId());
        assertEquals("govn2", actual.get(1).getMessage());
    }
}