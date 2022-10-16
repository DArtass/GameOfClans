package org.gameofclans;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

import org.gameofclans.controllers.ClanController;
import org.gameofclans.model.Clan;
import org.gameofclans.repo.DbClanRepository;
import org.gameofclans.service.ClanService;
import org.gameofclans.service.ClanServiceImpl;
import org.gameofclans.service.UserAddGoldService;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.jdbc.JDBCPool;

import java.sql.Connection;
import java.util.Optional;

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

        DbClanRepository dbClanRepository = new DbClanRepository(pool);
        ClanService clanService = new ClanServiceImpl(dbClanRepository);
        UserAddGoldService userAddGoldService = createUserAddGoldService(clanService);

        ClanController clanController = new ClanController(clanService);

        Javalin server = createWebServer(clanService)
                .routes(() -> {
                    path("clans", () -> {
                        path("{clanId}", () -> {
                            get(clanController::get);
                        });
                    });
                });

        server.start(8080);
    }
    public static void main(String[] args) throws Exception {
        new App();
    }

    private static UserAddGoldService createUserAddGoldService(ClanService clanService) {
        return new UserAddGoldService(clanService);
    }

   private static Javalin createWebServer(ClanService clans) {
        return Javalin.create(config -> {
        });
    }

}