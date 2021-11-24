package com.edu.cs.go.bet.server.mapper;

import com.edu.cs.go.bet.dathost.client.api.DatHostApi;
import com.edu.cs.go.bet.server.dto.match.CreateMatchRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateMatchRequestMapper {
    DatHostApi.APIpostMatchesRequest toDatHostDto(CreateMatchRequestDto createMatchRequestDto);
}
