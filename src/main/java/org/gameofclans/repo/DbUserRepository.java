package org.gameofclans.repo;

import org.gameofclans.model.User;
import org.hsqldb.jdbc.JDBCPool;

import java.util.Optional;

public class DbUserRepository extends DbRepositoryAbstract<User> {
    final private JDBCPool pool;

    public DbUserRepository(JDBCPool pool) {
        this.pool = pool;
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Update operation is not supported on user repository");
    }

    @Override
    public Optional<User> get(long userId) {
        return load(pool, userId, "users",
                rs -> new User(rs.getLong("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("salt"))
        );
    }
}
