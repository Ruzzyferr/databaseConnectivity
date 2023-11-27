package com.example.databaseconnectivitybypostman.dto;

import lombok.Data;

@Data
public class DatabaseInfoSaveRequestDto {

    private int id;

    private String host;

    private int port;

    private String dbName;

    private String username;

    private String password;
}
