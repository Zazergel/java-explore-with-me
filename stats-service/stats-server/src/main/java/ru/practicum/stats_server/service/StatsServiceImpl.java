package ru.practicum.stats_server.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.stats_dto.Constants;
import ru.practicum.stats_dto.model.EndpointHit;
import ru.practicum.stats_dto.model.ParamDto;
import ru.practicum.stats_dto.model.ViewStats;
import ru.practicum.stats_server.mapper.AppMapper;
import ru.practicum.stats_server.mapper.StatsMapper;
import ru.practicum.stats_server.model.App;
import ru.practicum.stats_server.repository.AppsRepository;
import ru.practicum.stats_server.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatsServiceImpl implements StatsService {
    private final AppsRepository appsRepository;
    private final StatsRepository statsRepository;
    private final StatsMapper statsMapper;
    private final AppMapper appMapper;

    @Override
    public void addHit(EndpointHit endpointHit) {
        App app = getOrCreateApp(endpointHit);
        log.info("Фиксируем обращение к {}", endpointHit);

        statsRepository.save(statsMapper.toStats(endpointHit,
                LocalDateTime.parse(endpointHit.getTimestamp(), Constants.DT_FORMATTER), app));
    }

    @Override
    public List<ViewStats> getStats(ParamDto paramDto) {
        log.info("Вывод списка обращений по параметрам start = {}, end = {}, uris = {}, unique = {}",
                paramDto.getStart(), paramDto.getEnd(), paramDto.getUris(), paramDto.getUnique());

        if (paramDto.getUris() == null || paramDto.getUris().isEmpty()) {
            if (paramDto.getUnique()) {
                log.info("Получен запрос статистики по всем событиям с уникальным IP");
                return statsRepository.getAllStatsDistinctIp(paramDto.getStart(), paramDto.getEnd());
            } else {
                log.info("Получен запрос статистики по всем событиям с без уникальности IP");
                return statsRepository.getAllStats(paramDto.getStart(), paramDto.getEnd());
            }

        } else {
            if (paramDto.getUnique()) {
                log.info("Получен запрос статистики по URI - {} с уникальным IP", paramDto.getUris());
                return statsRepository.getStatsByUrisDistinctIp(paramDto.getStart(), paramDto.getEnd(), paramDto.getUris());
            } else {
                log.info("Получен запрос статистики по URI - {} без уникальности IP", paramDto.getUris());
                return statsRepository.getStatsByUris(paramDto.getStart(), paramDto.getEnd(), paramDto.getUris());
            }

        }
    }

    private App getOrCreateApp(EndpointHit endpointHit) {
        return appsRepository.getAppByName(endpointHit.getApp())
                .orElseGet(() -> appsRepository.save(appMapper.toApp(endpointHit)));
    }
}