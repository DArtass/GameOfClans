package org.gameofclans.model;

import lombok.Data;

@Data
public class User {
    private final long id;     // id
    private final String login; // имя юзера
    private final String password;
    private final String salt;
}
