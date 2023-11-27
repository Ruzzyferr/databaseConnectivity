package com.example.databaseconnectivity.controller;

import com.example.databaseconnectivity.dto.DatabaseInfoDto;
import com.example.databaseconnectivity.dto.DatabaseInfoSaveRequestDto;
import com.example.databaseconnectivity.service.DatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-db-save")
public class DatabaseInfoController {

    private final DatabaseService databaseService;


    public DatabaseInfoController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }


    @PostMapping("/save")
    public ResponseEntity<DatabaseInfoDto> save(@RequestBody DatabaseInfoSaveRequestDto dto) {
        DatabaseInfoDto savedInfo = databaseService.save(dto);
        return ResponseEntity.ok(savedInfo);
    }

}
