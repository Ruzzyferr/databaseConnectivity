package com.example.databaseconnectivity.mapper;
import com.example.databaseconnectivity.dto.DatabaseInfoDto;
import com.example.databaseconnectivity.dto.DatabaseInfoSaveRequestDto;
import com.example.databaseconnectivity.entity.DatabaseInfo;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface DatabaseInfoMapper {


    DatabaseInfo toEntity (DatabaseInfoDto dto);

    DatabaseInfoDto toDto (DatabaseInfo entity);

    DatabaseInfo toEntityFromSaveRequestDto (DatabaseInfoSaveRequestDto dto);

}
