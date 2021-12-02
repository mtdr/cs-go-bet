package com.edu.cs.go.bet.server.mapper;

import com.edu.cs.go.bet.api.dto.match.CreateMatchRequestDto;
import com.edu.cs.go.bet.dathost.client.api.DatHostApi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateMatchRequestMapper {
    DatHostApi.APIpostMatchesRequest toDatHostDto(CreateMatchRequestDto createMatchRequestDto);
}
