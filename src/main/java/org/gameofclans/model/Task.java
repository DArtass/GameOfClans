package org.gameofclans.model;

import lombok.Data;

@Data
public class Task {
    private final long id;     // id
    private final String name; // имя таска
    private final int reward;    // награда
}
