package org.gameofclans.repo;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.jdbc.JDBCPool;
import org.junit.jupiter.api.AfterEach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RepoBaseTest {
    protected JDBCPool pool;
    void setupPool() throws Exception {
        pool = new JDBCPool();
        pool.setURL("jdbc:hsqldb:mem:test");
        pool.setUser("SA");
        pool.setPassword("");
        SqlFile sf = new SqlFile(getClass().getResource("/db/clans.sql"));
        try(Connection cnn = pool.getConnection()) {
            sf.setConnection(cnn);
            sf.execute();
        }
    }

    @AfterEach
    void teardown() throws SQLException {
        try(Connection cnn = pool.getConnection()) {
            try (PreparedStatement ps = cnn.prepareStatement("DROP SCHEMA PUBLIC CASCADE")) {
                ps.execute();
            }
        }
    }
}
