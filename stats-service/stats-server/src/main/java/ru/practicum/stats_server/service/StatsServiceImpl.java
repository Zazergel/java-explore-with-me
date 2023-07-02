package ru.practicum.stats_server.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.stats_dto.Constants;
import ru.practicum.stats_dto.model.EndpointHit;
import ru.practicum.stats_dto.model.ParamDto;
import ru.practicum.stats_dto.model.ViewStats;
import ru.practicum.stats_server.StatsMapper;
import ru.practicum.stats_server.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;
    private final StatsMapper statsMapper;

    @Override
    @Transactional
    public void addHit(EndpointHit endpointHit) {
        log.info("Фиксируем обращение к {}", endpointHit);

        statsRepository.save(statsMapper.toStats(endpointHit,
                LocalDateTime.parse(endpointHit.getTimestamp(), Constants.DT_FORMATTER)));
    }

    @Override
    public List<ViewStats> getStats(ParamDto paramDto) {
        log.info("Вывод списка обращений по параметрам start = {}, end = {}, uris = {}, unique = {}",
                paramDto.getStart(), paramDto.getEnd(), paramDto.getUris(), paramDto.getUnique());

        if (paramDto.getUris() == null || paramDto.getUris().isEmpty()) {
            if (paramDto.getUnique()) {
                return statsRepository.getAllStatsDistinctIp(paramDto.getStart(), paramDto.getEnd());
            } else {
                return statsRepository.getAllStats(paramDto.getStart(), paramDto.getEnd());
            }
        } else {
            if (paramDto.getUnique()) {
                return statsRepository.getStatsByUrisDistinctIp(paramDto.getStart(), paramDto.getEnd(), paramDto.getUris());
            } else {
                return statsRepository.getStatsByUris(paramDto.getStart(), paramDto.getEnd(), paramDto.getUris());
            }
        }
    }
}