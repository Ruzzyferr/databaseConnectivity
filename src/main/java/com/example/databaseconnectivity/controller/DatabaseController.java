package com.example.databaseconnectivity.controller;

import com.example.databaseconnectivity.PostgreSQLConnection;
import com.example.databaseconnectivity.Repository.DatabaseInfoRepository;
import com.example.databaseconnectivity.dto.DatabaseQueryDto;
import com.example.databaseconnectivity.dto.DatabaseInfoDto;
import com.example.databaseconnectivity.mapper.DatabaseInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/conndb")
public class DatabaseController {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseInfoRepository databaseInfoRepository;
    private final DatabaseInfoMapper databaseInfoMapper;

    @Autowired
    public DatabaseController(JdbcTemplate jdbcTemplate, DatabaseInfoRepository databaseInfoRepository, DatabaseInfoMapper databaseInfoMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.databaseInfoRepository = databaseInfoRepository;
        this.databaseInfoMapper = databaseInfoMapper;
    }

    @PostMapping("/executeQuery")
    public ResponseEntity<String> executeQuery(@RequestBody DatabaseQueryDto dto) {

        DatabaseInfoDto databaseInfoDto = databaseInfoMapper.toDto(databaseInfoRepository.findById(dto.getId()));

        jdbcTemplate.setDataSource(PostgreSQLConnection.createDataSource(databaseInfoDto.getHost(),
                databaseInfoDto.getPort(),
                databaseInfoDto.getDbName(),
                databaseInfoDto.getUsername(),
                databaseInfoDto.getPassword()));

        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(dto.getQuery());
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bağlantı hatası");
        }
    }

}
