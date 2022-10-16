package org.gameofclans.service;

import org.gameofclans.model.Clan;
import org.gameofclans.model.Task;

import java.util.Optional;

public class TaskService {
    private final ClanService clans;

    public TaskService(ClanService clans) {
        this.clans = clans;
    }

    Task get(long taskId){
        return null;
    }

    void completeTask(long clanId, long taskId) {
        Task task = get(taskId);
        //TODO not null
        Optional<Clan> clan = clans.get(clanId);


        // if (success)
        {
            //TODO thread sync
            //clan.setGold(clan.getGold() + task.getReward());
            // как-то сохранить изменения
            //clans.save(clan);
        }
    }
}
