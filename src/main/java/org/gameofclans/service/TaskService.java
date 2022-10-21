package org.gameofclans.service;

import org.gameofclans.model.Clan;
import org.gameofclans.model.Task;

import java.util.Optional;

public interface TaskService {
    Optional<Task> get(long taskId);
    void complete(long clanId, long userId, long taskId);
}
