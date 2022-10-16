package org.gameofclans.repo;

import org.gameofclans.model.Clan;
import org.hsqldb.jdbc.JDBCPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DbClanRepositoryTest extends RepoBaseTest{
    private DbClanRepository dbClanRepository;
    @BeforeEach
    void setup() throws Exception {
        setupPool();
        dbClanRepository = new DbClanRepository(pool);
    }
    @Test
    void getExisting() {
        Optional<Clan> actual = dbClanRepository.get(1);

        Clan expected = new Clan(1,"champions", 777);
        assertEquals(Optional.of(expected), actual);
    }

    @Test
    void getNonExisting() {
        Optional<Clan> actual = dbClanRepository.get(55);

        assertTrue(actual.isEmpty());
    }

    @Test
    void getWrongDB() {
        JDBCPool pool = new JDBCPool();

        pool.setURL("jdbc:hsqldb:mem:non-existing");
        pool.setUser("SA");
        pool.setPassword("");
        DbClanRepository dbClanRepository = new DbClanRepository(pool);
        assertThrows(
                RuntimeException.class,
                () -> dbClanRepository.get(1)
        );
    }

    @Test
    void update() {
        int testId = 2;
        Clan expected = new Clan(testId, "test clan", 1200);
        dbClanRepository.update(expected);
        Optional<Clan> actual = dbClanRepository.get(testId);
        assertEquals(Optional.of(expected), actual);
    }
}