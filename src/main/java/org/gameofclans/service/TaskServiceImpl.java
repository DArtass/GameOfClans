package org.gameofclans.service;

import org.gameofclans.model.Clan;
import org.gameofclans.model.Task;
import org.gameofclans.repo.DbClanRepository;
import org.gameofclans.repo.DbTaskRepository;
import org.gameofclans.repo.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TaskServiceImpl implements TaskService {
    private final ClanService clanService;
    private final Repository<Task> repo;
    private final AuditService auditService;
    public TaskServiceImpl(ClanService clanService, DbTaskRepository repo, AuditService auditService) {
        this.clanService = clanService;
        this.repo = repo;
        this.auditService = auditService;
    }

    @Override
    public Optional<Task> get(long taskId) {
        return repo.get(taskId);
    }

    public void complete(long clanId, long userId, long taskId) {

          repo.get(taskId).ifPresentOrElse(task -> {
                auditService.add("Complete task %s for %d gold", task, task.getReward());
                clanService.addGold(clanId, userId, task.getReward());
                },
                    () -> {
                throw new RuntimeException("Task not found by id: " + taskId);
            });
    }
}
