package com.example.databaseconnectivitybypostman.Repository;

import com.example.databaseconnectivitybypostman.entity.DatabaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseInfoRepository extends JpaRepository<DatabaseInfo, Integer> {

    DatabaseInfo findById(int id);

}
