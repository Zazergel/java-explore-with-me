package ru.practicum.main_service.event.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.main_service.MainConstants;
import ru.practicum.main_service.event.enums.EventStateAction;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UpdateEventAdminRequest {
    @Size(min = MainConstants.MIN_LENGTH_ANNOTATION, max = MainConstants.MAX_LENGTH_ANNOTATION)
    String annotation;

    Long category;

    @Size(min = MainConstants.MIN_LENGTH_DESCRIPTION, max = MainConstants.MAX_LENGTH_DESCRIPTION)
    String description;

    @JsonFormat(pattern = MainConstants.DT_FORMAT, shape = JsonFormat.Shape.STRING)
    LocalDateTime eventDate;

    @Valid
    LocationDto location;

    Boolean paid;

    @PositiveOrZero
    Integer participantLimit;

    Boolean requestModeration;

    EventStateAction stateAction;

    @Size(min = MainConstants.MIN_LENGTH_TITLE, max = MainConstants.MAX_LENGTH_TITLE)
    String title;
}