package org.gameofclans.repo;

import org.gameofclans.model.Task;
import org.hsqldb.jdbc.JDBCPool;

import java.util.Optional;

public class DbTaskRepository extends DbRepositoryAbstract<Task> {
    final private JDBCPool pool;

    public DbTaskRepository(JDBCPool pool) {
        this.pool = pool;
    }

    @Override
    public void update(Task task) {
        throw new UnsupportedOperationException("Update operation is not supported on task repository");
    }

    @Override
    public Optional<Task> get(long taskId) {
        return load(pool, taskId, "tasks",
                rs -> new Task(rs.getLong("id"), rs.getString("name"), rs.getInt("reward"))
        );
    }
}
