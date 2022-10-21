package org.gameofclans.controllers;

import io.javalin.http.Context;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gameofclans.service.TaskService;

public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public void complete(Context context) {
        long taskId = Long.parseLong(context.pathParam("taskId"));
        CompleteTask data = context.bodyAsClass(CompleteTask.class);

        taskService.complete(taskId, data.userId, data.clanId);
    }
    @Data
    static class CompleteTask {
        private long userId;
        private long clanId;
    }
}
