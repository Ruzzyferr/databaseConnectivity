package com.example.databaseconnectivity.service;

import com.example.databaseconnectivity.Repository.UrlInfoRepository;
import com.example.databaseconnectivity.dto.UrlInfoDto;
import com.example.databaseconnectivity.dto.UrlInfoSaveRequestDto;
import com.example.databaseconnectivity.entity.UrlInfo;
import com.example.databaseconnectivity.mapper.UrlInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final UrlInfoMapper urlInfoMapper;
    private final UrlInfoRepository urlInfoRepository;

    public DatabaseService(UrlInfoMapper urlInfoMapper, UrlInfoRepository urlInfoRepository) {
        this.urlInfoMapper = urlInfoMapper;
        this.urlInfoRepository = urlInfoRepository;
    }


    public UrlInfoDto save(UrlInfoSaveRequestDto dto) {
        UrlInfo urlInfo = urlInfoMapper.toEntityFromSaveRequestDto(dto);
        UrlInfoDto urlInfoDto = urlInfoMapper.toDto(urlInfoRepository.save(urlInfo));



        return urlInfoDto;
    }
}
