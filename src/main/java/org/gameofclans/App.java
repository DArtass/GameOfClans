package org.gameofclans;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

import org.gameofclans.controllers.AuditController;
import org.gameofclans.controllers.ClanController;
import org.gameofclans.controllers.TaskController;
import org.gameofclans.repo.DbAuditRepository;
import org.gameofclans.repo.DbClanRepository;
import org.gameofclans.repo.DbTaskRepository;
import org.gameofclans.service.*;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.jdbc.JDBCPool;

import java.sql.Connection;

public class App {
    public App() throws Exception {
        JDBCPool pool = new JDBCPool();
        pool.setURL("jdbc:hsqldb:mem:clans");
        pool.setUser("SA");
        pool.setPassword("");
        SqlFile sf = new SqlFile(getClass().getResource("/db/clans.sql"));
        try(Connection cnn = pool.getConnection()) {
            sf.setConnection(cnn);
            sf.execute();
        }

        DbAuditRepository audit = new DbAuditRepository(pool);
        AuditService auditService = new AuditServiceImpl(audit);
        DbClanRepository dbClanRepository = new DbClanRepository(pool);
        DbTaskRepository dbTaskRepository = new DbTaskRepository(pool);
        ClanService clanService = new ClanServiceImpl(dbClanRepository, auditService);
        TaskService taskService = new TaskServiceImpl(clanService, dbTaskRepository, auditService);

        ClanController clanController = new ClanController(clanService);
        AuditController auditController = new AuditController(auditService);
        TaskController taskController = new TaskController(taskService);
        Javalin server = createWebServer(clanService)
                .routes(() -> {
                    path("clans", () ->
                            path("{clanId}", () -> {
                                get(clanController::get);
                                post(clanController::addGold);
                            }));
                    path("tasks", () ->
                            path("{taskId}", () ->
                                    post(taskController::complete)));
                    path("audit", () ->
                            get(auditController::get));

                });

        server.start(8080);
    }
    public static void main(String[] args) throws Exception {
        new App();
    }

   private static Javalin createWebServer(ClanService clans) {
        return Javalin.create(config -> {
        });
    }

}