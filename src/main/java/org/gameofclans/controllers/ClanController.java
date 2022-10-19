package org.gameofclans.controllers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import lombok.Data;
import org.gameofclans.model.Clan;
import org.gameofclans.service.ClanService;

import java.util.Optional;

public class ClanController {
    private final ClanService clanService;

    public ClanController(ClanService clanService) {
        this.clanService = clanService;
    }

    public void get(Context context) {
        long clanId = Long.parseLong(context.pathParam("clanId"));
        clanService.get(clanId)
                .map(context::json)
                .orElse(context.status(404));
    }

    public void addGold(Context context) {
        long clanId = Long.parseLong(context.pathParam("clanId"));
        AddUserGold data = context.bodyAsClass(AddUserGold.class);

        clanService.addGold(clanId, data.userId, data.gold);
    }
    @Data
    static class AddUserGold {
        long userId;
        int gold;
    }
}
