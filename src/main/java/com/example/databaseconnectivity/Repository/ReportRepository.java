package com.example.databaseconnectivity.Repository;

import com.example.databaseconnectivity.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {

}
