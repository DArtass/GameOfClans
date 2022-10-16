package org.gameofclans.repo;

import org.gameofclans.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DbTaskRepositoryTest extends RepoBaseTest{
    private DbTaskRepository dbTaskRepository;
    @BeforeEach
    void setup() throws Exception {
        setupPool();
        dbTaskRepository = new DbTaskRepository(pool);
    }
    @Test
    void getExisting() {
        Optional<Task> actual = dbTaskRepository.get(1);

        Task expected = new Task(1,"arena", 100);
        assertEquals(Optional.of(expected), actual);
    }

    @Test
    void getNonExisting() {
        Optional<Task> actual = dbTaskRepository.get(55);

        assertTrue(actual.isEmpty());
    }
}