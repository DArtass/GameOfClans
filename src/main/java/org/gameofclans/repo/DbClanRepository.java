package org.gameofclans.repo;

import org.gameofclans.model.Clan;
import org.hsqldb.jdbc.JDBCPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class DbClanRepository extends DbRepositoryAbstract<Clan> {
    final private JDBCPool pool;

    public DbClanRepository(JDBCPool pool) {
        this.pool = pool;
    }

    @Override
    public Optional<Clan> get(long clanId) {
        return load(pool, clanId, "clans",
                rs -> new Clan(rs.getLong("id" ), rs.getString("name"), rs.getInt("gold"))
        );
    }

    @Override
    public void update(Clan clan) {
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("update clans set name = ?, gold = ? where id = ?")) {
                ps.setString(1, clan.getName());
                ps.setInt(2, clan.getGold());
                ps.setLong(3, clan.getId());
                if (ps.executeUpdate() == 1) {
                    connection.commit();
                } else {
                    connection.rollback();
                    throw new RuntimeException("Update non existing clan id:" + clan.getId());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
