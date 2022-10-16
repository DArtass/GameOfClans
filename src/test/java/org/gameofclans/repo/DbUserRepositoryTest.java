package org.gameofclans.repo;

import org.gameofclans.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DbUserRepositoryTest extends RepoBaseTest{
    private DbUserRepository dbUserRepository;
    @BeforeEach
    void setup() throws Exception {
        setupPool();
        dbUserRepository = new DbUserRepository(pool);
    }
    @Test
    void getExisting() {
        Optional<User> actual = dbUserRepository.get(1);

        User expected = new User(1,"vasya", "ebe69dac496de00ccf03ff960b49a7fa6c03c91e", "test1");
        assertEquals(Optional.of(expected), actual);
    }

    @Test
    void getNonExisting() {
        Optional<User> actual = dbUserRepository.get(55);

        assertTrue(actual.isEmpty());
    }
}