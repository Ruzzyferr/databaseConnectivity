package com.example.databaseconnectivitybypostman.dto;

import lombok.Data;

@Data
public class DatabaseInfoDto {
    private int id;

    private String host;

    private int port;

    private String dbName;

    private String username;

    private String password;
}
