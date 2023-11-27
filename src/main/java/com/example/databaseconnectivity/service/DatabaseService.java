package com.example.databaseconnectivity.service;

import com.example.databaseconnectivity.Repository.DatabaseInfoRepository;
import com.example.databaseconnectivity.dto.DatabaseInfoDto;
import com.example.databaseconnectivity.dto.DatabaseInfoSaveRequestDto;
import com.example.databaseconnectivity.entity.DatabaseInfo;
import com.example.databaseconnectivity.mapper.DatabaseInfoMapper;
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
