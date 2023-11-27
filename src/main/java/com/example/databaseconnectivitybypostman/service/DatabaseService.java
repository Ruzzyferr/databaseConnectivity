package com.example.databaseconnectivitybypostman.service;

import com.example.databaseconnectivitybypostman.Repository.DatabaseInfoRepository;
import com.example.databaseconnectivitybypostman.dto.DatabaseInfoDto;
import com.example.databaseconnectivitybypostman.dto.DatabaseInfoSaveRequestDto;
import com.example.databaseconnectivitybypostman.entity.DatabaseInfo;
import com.example.databaseconnectivitybypostman.mapper.DatabaseInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final DatabaseInfoMapper databaseInfoMapper;
    private final DatabaseInfoRepository databaseInfoRepository;

    public DatabaseService(DatabaseInfoMapper databaseInfoMapper, DatabaseInfoRepository databaseInfoRepository) {
        this.databaseInfoMapper = databaseInfoMapper;
        this.databaseInfoRepository = databaseInfoRepository;
    }


    public DatabaseInfoDto save(DatabaseInfoSaveRequestDto dto) {
        DatabaseInfo databaseInfo = databaseInfoMapper.toEntityFromSaveRequestDto(dto);
        DatabaseInfoDto databaseInfoDto = databaseInfoMapper.toDto(databaseInfoRepository.save(databaseInfo));



        return databaseInfoDto;
    }
}
