package ru.practicum.stats_server;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.stats_server.service.StatsService;
import ru.practicum.stats_dto.Constants;
import ru.practicum.stats_dto.model.EndpointHit;
import ru.practicum.stats_dto.model.ViewStats;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatsController {
    private final StatsService statsService;

    @PostMapping(Constants.HIT_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addHit(@Valid @RequestBody EndpointHit endpointHit) {
        statsService.addHit(endpointHit);
    }

    @GetMapping(Constants.STATS_ENDPOINT)
    public List<ViewStats> getStats(@RequestParam @DateTimeFormat(pattern = Constants.DT_FORMAT) LocalDateTime start,
                                    @RequestParam @DateTimeFormat(pattern = Constants.DT_FORMAT) LocalDateTime end,
                                    @RequestParam List<String> uris,
                                    @RequestParam(defaultValue = "false") Boolean unique) {
        if (start.isAfter(end) || start.isEqual(end)) {
            throw new IllegalArgumentException("Недопустимый временной промежуток.");
        }
        return statsService.getStats(start, end, uris, unique);
    }
}