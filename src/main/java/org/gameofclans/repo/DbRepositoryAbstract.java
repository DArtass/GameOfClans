package org.gameofclans.repo;

import org.hsqldb.jdbc.JDBCPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public abstract class DbRepositoryAbstract<T> implements Repository<T> {
    protected Optional<T> load(JDBCPool pool, long id, String table, OmnivoreFunction<ResultSet, T> creator) {
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("select * from " + table + " where id = ?")) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(creator.apply(rs));
                    } else {
                        return Optional.empty();
                    }
                }
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FunctionalInterface
    public interface OmnivoreFunction<T, R> {
        R apply(T arg) throws SQLException;
    }
}

