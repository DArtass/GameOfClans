package org.gameofclans.service;

import org.gameofclans.model.Clan;

import java.util.Optional;

public interface ClanService {
    Optional<Clan> get(long clanId);
}
