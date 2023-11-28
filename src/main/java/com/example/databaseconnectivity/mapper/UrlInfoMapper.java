package com.example.databaseconnectivity.mapper;
import com.example.databaseconnectivity.dto.UrlInfoDto;
import com.example.databaseconnectivity.dto.UrlInfoSaveRequestDto;
import com.example.databaseconnectivity.entity.UrlInfo;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UrlInfoMapper {


    UrlInfo toEntity (UrlInfoDto dto);
    //check

    UrlInfoDto toDto (UrlInfo entity);

    UrlInfo toEntityFromSaveRequestDto (UrlInfoSaveRequestDto dto);

}
