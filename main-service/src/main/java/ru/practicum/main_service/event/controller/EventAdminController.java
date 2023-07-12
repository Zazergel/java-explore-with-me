package ru.practicum.main_service.event.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_service.MainConstants;
import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.dto.UpdateEventAdminRequest;
import ru.practicum.main_service.event.enums.EventState;
import ru.practicum.main_service.event.service.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/events")
@Validated
public class EventAdminController {
    private final EventService eventService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventFullDto> getEventsByAdmin(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<EventState> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) @DateTimeFormat(pattern = MainConstants.DT_FORMAT) LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = MainConstants.DT_FORMAT) LocalDateTime rangeEnd,
            @RequestParam(defaultValue = MainConstants.PAGE_DEFAULT_FROM) @PositiveOrZero Integer from,
            @RequestParam(defaultValue = MainConstants.PAGE_DEFAULT_SIZE) @Positive Integer size) {
        return eventService.getEventsByAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto patchEventByAdmin(@PathVariable Long eventId,
                                          @Valid @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        return eventService.patchEventByAdmin(eventId, updateEventAdminRequest);
    }
}