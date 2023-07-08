package ru.practicum.stats_server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.stats_dto.model.ViewStats;
import ru.practicum.stats_server.model.Stats;

import java.time.LocalDateTime;
import java.util.List;


public interface StatsRepository extends JpaRepository<Stats, Long> {
    @Query(nativeQuery = true, name = "getStatsByUris")
    List<ViewStats> getStatsByUris(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(nativeQuery = true, name = "getStatsByUrisDistinctIp")
    List<ViewStats> getStatsByUrisDistinctIp(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(nativeQuery = true, name = "getAllStats")
    List<ViewStats> getAllStats(LocalDateTime start, LocalDateTime end);

    @Query(nativeQuery = true, name = "getAllStatsDistinctIp")
    List<ViewStats> getAllStatsDistinctIp(LocalDateTime start, LocalDateTime end);


}