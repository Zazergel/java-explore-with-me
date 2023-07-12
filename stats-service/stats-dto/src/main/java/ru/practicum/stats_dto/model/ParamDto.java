package ru.practicum.stats_dto.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParamDto {
    LocalDateTime start;
    LocalDateTime end;
    List<String> uris;
    Boolean unique;
}
