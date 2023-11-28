package com.example.databaseconnectivity.Repository;

import com.example.databaseconnectivity.entity.UrlInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlInfoRepository extends JpaRepository<UrlInfo, Integer> {

    UrlInfo findById(int id);

}
