package com.example.databaseconnectivity.controller;

import com.example.databaseconnectivity.dto.UrlInfoDto;
import com.example.databaseconnectivity.dto.UrlInfoSaveRequestDto;
import com.example.databaseconnectivity.service.DatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db-url-save")
public class UrlInfoController {

    private final DatabaseService databaseService;


    public UrlInfoController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }


    @PostMapping("/save")
    public ResponseEntity<UrlInfoDto> save(@RequestBody UrlInfoSaveRequestDto dto) {
        UrlInfoDto savedInfo = databaseService.save(dto);
        return ResponseEntity.ok(savedInfo);
    }

}
