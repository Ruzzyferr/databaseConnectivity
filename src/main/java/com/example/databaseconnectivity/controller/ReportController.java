package com.example.databaseconnectivity.controller;

import com.example.databaseconnectivity.PostgreSQLConnection;
import com.example.databaseconnectivity.Repository.ReportRepository;
import com.example.databaseconnectivity.dto.UrlInfoDto;
import com.example.databaseconnectivity.entity.Report;
import com.example.databaseconnectivity.mapper.UrlInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private final ReportRepository reportRepository;
    private final UrlInfoMapper urlInfoMapper;
    private final JdbcTemplate jdbcTemplate;

    public ReportController(ReportRepository reportRepository, UrlInfoMapper urlInfoMapper, JdbcTemplate jdbcTemplate) {
        this.reportRepository = reportRepository;
        this.urlInfoMapper = urlInfoMapper;
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping("/{reportId}")
    public ResponseEntity<String> getReport(@PathVariable int reportId ) {

        Optional<Report> optionalReport = reportRepository.findById(reportId);

        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            UrlInfoDto urlInfoDto = urlInfoMapper.toDto(report.getUrlInfo());

            jdbcTemplate.setDataSource(PostgreSQLConnection.createDataSource(urlInfoDto.getHost(),
                    urlInfoDto.getPort(),
                    urlInfoDto.getDbName(),
                    urlInfoDto.getUsername(),
                    urlInfoDto.getPassword()));

            Map<String, Object> reportInfo = new HashMap<>();
            reportInfo.put("reportId", report.getId());
            reportInfo.put("createdAt", report.getCreatedAt());
            reportInfo.put("query", report.getQuery());


            // Sorgu sonuçlarını al
            List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(report.getQuery());

            // Rapor bilgileri ve sorgu sonuçlarını birleştirme
            List<Map<String, Object>> result = new ArrayList<>();
            result.add(reportInfo); // Rapor bilgilerini ilk önce ekleyin
            result.addAll(queryResults); // Sonra sorgu sonuçlarını ekleyin

            return ResponseEntity.ok(result.toString());

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{reportId}/filter")
    public ResponseEntity<String> filterReport(@PathVariable int reportId, @RequestParam String filterCriteria) {
        Optional<Report> optionalReport = reportRepository.findById(reportId);

        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            UrlInfoDto urlInfoDto = urlInfoMapper.toDto(report.getUrlInfo());

            jdbcTemplate.setDataSource(PostgreSQLConnection.createDataSource(urlInfoDto.getHost(),
                    urlInfoDto.getPort(),
                    urlInfoDto.getDbName(),
                    urlInfoDto.getUsername(),
                    urlInfoDto.getPassword()));

            String originalQuery = report.getQuery();

            List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(originalQuery);

            List<Map<String, Object>> filteredResults = new ArrayList<>();
            for (Map<String, Object> row : queryResults) {
                for (Map.Entry<String, Object> entry : row.entrySet()) {
                    if (entry.getValue().toString().toLowerCase().equals(filterCriteria)) {
                        filteredResults.add(row);
                        break;
                    }
                }
            }

            return ResponseEntity.ok(filteredResults.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
