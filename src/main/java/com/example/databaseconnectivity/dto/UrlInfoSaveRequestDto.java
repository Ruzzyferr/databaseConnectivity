package com.example.databaseconnectivity.dto;

import lombok.Data;

@Data
public class UrlInfoSaveRequestDto {

    private int id;

    private String host;

    private int port;

    private String dbName;

    private String username;

    private String password;
}
