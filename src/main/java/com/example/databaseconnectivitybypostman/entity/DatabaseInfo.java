package com.example.databaseconnectivitybypostman.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "UserDatabaseInfo")
public class DatabaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String host;

    private int port;

    private String dbName;

    private String username;

    private String password;

}
