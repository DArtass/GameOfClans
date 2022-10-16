package org.gameofclans.service;

import org.gameofclans.model.Clan;
import org.gameofclans.repo.DbClanRepository;

import java.util.Optional;

public class ClanServiceImpl implements ClanService {
    private DbClanRepository repo;

    public ClanServiceImpl(DbClanRepository dbClanRepository) {
        this.repo = dbClanRepository;
    }

    @Override
    public Optional<Clan> get(long clanId) {
        return repo.get(clanId);
    }
}
