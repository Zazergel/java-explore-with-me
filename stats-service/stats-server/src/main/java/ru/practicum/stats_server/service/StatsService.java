package ru.practicum.stats_server.service;


import ru.practicum.stats_dto.model.EndpointHit;
import ru.practicum.stats_dto.model.ParamDto;
import ru.practicum.stats_dto.model.ViewStats;

import java.util.List;

public interface StatsService {
    void addHit(EndpointHit endpointHit);

    List<ViewStats> getStats(ParamDto paramDto);
}