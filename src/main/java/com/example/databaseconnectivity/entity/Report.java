package com.example.databaseconnectivity.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private UrlInfo urlInfo;

    private String query;

    private LocalDateTime createdAt;

}
