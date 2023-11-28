package com.example.databaseconnectivity.controller;

import com.example.databaseconnectivity.PostgreSQLConnection;
import com.example.databaseconnectivity.Repository.UrlInfoRepository;
import com.example.databaseconnectivity.Repository.ReportRepository;
import com.example.databaseconnectivity.dto.DatabaseQueryDto;
import com.example.databaseconnectivity.dto.UrlInfoDto;
import com.example.databaseconnectivity.entity.Report;
import com.example.databaseconnectivity.mapper.UrlInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/conndb")
public class DatabaseController {

    private final JdbcTemplate jdbcTemplate;
    private final UrlInfoRepository urlInfoRepository;
    private final UrlInfoMapper urlInfoMapper;
    @Autowired
    private final ReportRepository reportRepository;

    @Autowired
    public DatabaseController(JdbcTemplate jdbcTemplate, UrlInfoRepository urlInfoRepository, UrlInfoMapper urlInfoMapper, ReportRepository reportRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.urlInfoRepository = urlInfoRepository;
        this.urlInfoMapper = urlInfoMapper;
        this.reportRepository = reportRepository;
    }

    @PostMapping("/executeQuery")
    public ResponseEntity<String> executeQuery(@RequestBody DatabaseQueryDto dto) {

        UrlInfoDto urlInfoDto = urlInfoMapper.toDto(urlInfoRepository.findById(dto.getId()));

        jdbcTemplate.setDataSource(PostgreSQLConnection.createDataSource(urlInfoDto.getHost(),
                urlInfoDto.getPort(),
                urlInfoDto.getDbName(),
                urlInfoDto.getUsername(),
                urlInfoDto.getPassword()));

        if (dto.getCreateReport().equals(true)) {

            Report report = new Report();
            report.setUrlInfo(urlInfoMapper.toEntity(urlInfoDto));
            report.setQuery(dto.getQuery());
            report.setCreatedAt(LocalDateTime.now());

            reportRepository.save(report);

            try {
                List<Map<String, Object>> result = jdbcTemplate.queryForList(dto.getQuery());
                return ResponseEntity.ok(result.toString());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bağlantı hatası");
            }

        } else {

            try {
                List<Map<String, Object>> result = jdbcTemplate.queryForList(dto.getQuery());
                return ResponseEntity.ok(result.toString());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bağlantı hatası");
            }
        }
    }
}
