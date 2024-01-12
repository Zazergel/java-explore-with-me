package ru.practicum.main_service.event.service;


import org.springframework.data.domain.Pageable;
import ru.practicum.main_service.event.dto.*;
import ru.practicum.main_service.event.enums.EventSortType;
import ru.practicum.main_service.event.model.Event;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<EventFullDto> getEventsByAdmin(EventAdminControllerParamDto paramDto);

    EventFullDto patchEventByAdmin(Long eventId, UpdateEventAdminRequest updateEventAdminRequest);

    List<EventShortDto> getAllEventsByPrivate(Long userId, Pageable pageable);

    EventFullDto createEventByPrivate(Long userId, NewEventDto newEventDto);

    EventFullDto getEventByPrivate(Long userId, Long eventId);

    EventFullDto patchEventByPrivate(Long userId, Long eventId, UpdateEventUserRequest updateEventUserRequest);

    List<EventShortDto> getEventsByPublic(String text,
                                          List<Long> categories,
                                          boolean paid,
                                          LocalDateTime rangeStart,
                                          LocalDateTime rangeEnd,
                                          boolean onlyAvailable,
                                          EventSortType sort,
                                          Integer from,
                                          Integer size,
                                          HttpServletRequest request);

    EventFullDto getEventByPublic(Long id, HttpServletRequest request);

    Event getEventById(Long eventId);

    List<Event> getEventsByIds(List<Long> eventsId);

    List<EventShortDto> toEventsShortDto(List<Event> events);
}