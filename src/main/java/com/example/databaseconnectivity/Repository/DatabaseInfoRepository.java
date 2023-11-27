package com.example.databaseconnectivity.Repository;

import com.example.databaseconnectivity.entity.DatabaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseInfoRepository extends JpaRepository<DatabaseInfo, Integer> {

    DatabaseInfo findById(int id);

}
