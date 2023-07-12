package ru.practicum.main_service.event.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main_service.MainConstants;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.event.repository.RequestRepository;
import ru.practicum.stats_client.StatsClient;
import ru.practicum.stats_dto.model.ViewStats;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StatsServiceImpl implements StatsService {
    private final StatsClient statsClient;
    private final RequestRepository requestRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    @Value(value = "${app.name}")
    private String appName;

    @Override
    public void addHit(HttpServletRequest request) {
        log.info("Отправлен запрос на регистрацию обращения к серверу статистики с параметрами request = {}", request);

        statsClient.addHit(appName, request.getRequestURI(), request.getRemoteAddr(),
                LocalDateTime.parse(LocalDateTime.now().format(MainConstants.DT_FORMATTER), MainConstants.DT_FORMATTER));
    }

    @Override
    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        log.info("Отправлен запрос на получение статистики к серверу статистики с параметрами " +
                "start = {}, end = {}, uris = {}, unique = {}", start, end, uris, unique);

        ResponseEntity<Object> response = statsClient.getStats(start, end, uris, unique);

        try {
            return Arrays.asList(mapper.readValue(mapper.writeValueAsString(response.getBody()), ViewStats[].class));
        } catch (IOException exception) {
            throw new ClassCastException(exception.getMessage());
        }
    }

    @Override
    public Map<Long, Long> getViews(List<Event> events) {
        log.info("Отправлен запрос на получение статистики уникальных посещений в виде Map<eventId, count> " +
                "для списка событий.");

        Map<Long, Long> views = new HashMap<>();

        List<Event> publishedEvents = getPublished(events);

        if (events.isEmpty()) {
            return views;
        }

        Optional<LocalDateTime> minPublishedOn = publishedEvents.stream()
                .map(Event::getPublishedOn)
                .filter(Objects::nonNull)
                .min(LocalDateTime::compareTo);

        if (minPublishedOn.isPresent()) {
            LocalDateTime start = LocalDateTime.now().minusDays(365);
            LocalDateTime end = LocalDateTime.now().plusHours(2);
            List<String> uris = publishedEvents.stream()
                    .map(Event::getId)
                    .map(id -> ("/events/" + id))
                    .collect(Collectors.toList());

            List<ViewStats> viewStats = getStats(start, end, uris, true);
            viewStats.forEach(viewStat -> {
                Long eventId = Long.parseLong(viewStat.getUri().split("/", 0)[2]);
                views.put(eventId, views.getOrDefault(eventId, 0L) + viewStat.getHits());
            });
        }

        return views;
    }

    @Override
    public Map<Long, Long> getConfirmedRequests(List<Event> events) {
        List<Long> eventsId = getPublished(events).stream()
                .map(Event::getId)
                .collect(Collectors.toList());

        Map<Long, Long> requestStats = new HashMap<>();

        if (!eventsId.isEmpty()) {
            requestRepository.getConfirmedRequests(eventsId)
                    .forEach(stat -> requestStats.put(stat.getEventId(), stat.getConfirmedRequests()));
        }

        return requestStats;
    }

    private List<Event> getPublished(List<Event> events) {
        return events.stream()
                .filter(event -> event.getPublishedOn() != null)
                .collect(Collectors.toList());
    }
}