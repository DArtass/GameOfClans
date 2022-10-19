package org.gameofclans.service;

import org.gameofclans.model.Clan;
import org.gameofclans.repo.AuditRepository;
import org.gameofclans.repo.Repository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class ClanServiceImplTest {

    @Test
    void addGold() {
        Repository<Clan> repo = mock(Repository.class);
        AuditService audit = mock(AuditService.class);

        ClanServiceImpl clanService = new ClanServiceImpl(repo, audit);
        when(repo.get(1)).thenReturn(Optional.of(new Clan(1,"qwe",10)));
        clanService.addGold(1,1,15);

        verify(repo).update(new Clan(1,"qwe", 25));

        verify(audit).add("Add gold %d for clan:%d by user:%d. Result: %s",
                15,
                1L,
                1L,
                new Clan(1, "qwe", 25));
    }
}