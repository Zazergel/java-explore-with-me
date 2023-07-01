package ru.practicum.stats_server;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.stats_dto.model.EndpointHit;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface StatsMapper {
    @Mapping(target = "timestamp", expression = "java(timestamp)")
    Stats toStats(EndpointHit endpointHit, LocalDateTime timestamp);
}