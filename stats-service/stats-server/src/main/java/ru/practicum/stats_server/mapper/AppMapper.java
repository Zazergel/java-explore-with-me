package ru.practicum.stats_server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.stats_dto.model.EndpointHit;
import ru.practicum.stats_server.model.App;

@Mapper(componentModel = "spring")
public interface AppMapper {

    @Mapping(source = "app", target = "name")
    App toApp(EndpointHit endpointHit);

}
