package org.gameofclans.repo;

import org.gameofclans.model.AuditMessage;
import org.hsqldb.jdbc.JDBCPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbAuditRepository implements AuditRepository {
    final private JDBCPool pool;

    public DbAuditRepository(JDBCPool pool) {
        this.pool = pool;
    }

    @Override
    public List<AuditMessage> list() {
        List<AuditMessage> result = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("select * from audit order by id")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result.add(new AuditMessage(rs.getLong("id"),
                                rs.getString("message"),
                                rs.getTimestamp("ts").toLocalDateTime()));
                    }
                    return result;
                }
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String info) {
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("insert into audit (message) values (?)")) {
                ps.setString(1, info);
                if (ps.executeUpdate() == 1) {
                    connection.commit();
                } else {
                    connection.rollback();
                    throw new RuntimeException("Info can't be insert" + info);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
