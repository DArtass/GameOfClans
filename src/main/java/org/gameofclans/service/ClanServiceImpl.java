package org.gameofclans.service;

import org.gameofclans.model.Clan;
import org.gameofclans.repo.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ClanServiceImpl implements ClanService {
    private final Repository<Clan> repo;
    private final AuditService audit;
    private final Map<Long, Object> locks;

    public ClanServiceImpl(Repository<Clan> dbClanRepository, AuditService audit) {
        this.repo = dbClanRepository;
        this.audit = audit;
        locks = new ConcurrentHashMap<>();
    }

    @Override
    public void addGold(long clanId, long userId, int add) {
        //TODO check valid user

        //плюсы
        // - можно использовать репозиторий не поддерживающий транзакции
        //минусы:
        // - Не будет работать при скалировании микросервиса
        // - Нужно поддерживать таблицу блокировок, она занимает память
        synchronized (locks.computeIfAbsent(clanId, k -> new Object())) {
            repo.get(clanId).ifPresentOrElse(clan -> {
                clan.setGold(clan.getGold() + add);
                repo.update(clan);

                audit.add("Add gold %d for clan:%d by user:%d. Result: %s", add, clanId, userId, clan);
            },
            () -> { throw new RuntimeException("Clan not found by id: " + clanId); });

            locks.remove(clanId);
        }
    }

    @Override
    public Optional<Clan> get(long clanId) {
        return repo.get(clanId);
    }
}