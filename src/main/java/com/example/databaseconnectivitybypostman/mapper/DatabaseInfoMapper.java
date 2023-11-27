package com.example.databaseconnectivitybypostman.mapper;
import com.example.databaseconnectivitybypostman.dto.DatabaseInfoDto;
import com.example.databaseconnectivitybypostman.dto.DatabaseInfoSaveRequestDto;
import com.example.databaseconnectivitybypostman.entity.DatabaseInfo;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface DatabaseInfoMapper {


    DatabaseInfo toEntity (DatabaseInfoDto dto);



    DatabaseInfoDto toDto (DatabaseInfo entity);

    DatabaseInfo toEntityFromSaveRequestDto (DatabaseInfoSaveRequestDto dto);

}
