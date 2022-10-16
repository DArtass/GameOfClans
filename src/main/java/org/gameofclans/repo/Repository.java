package org.gameofclans.repo;

import java.util.Optional;

public interface Repository<T> {
    Optional<T> get(long id);
    void update(T task);
}
