package ru.practicum.stats_server.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.stats_dto.model.EndpointHit;
import ru.practicum.stats_server.model.App;
import ru.practicum.stats_server.model.Stats;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface StatsMapper {
    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "timestamp", expression = "java(timestamp)")
    @Mapping(target = "app", expression = "java(app)")
    Stats toStats(EndpointHit endpointHit, LocalDateTime timestamp, App app);
}