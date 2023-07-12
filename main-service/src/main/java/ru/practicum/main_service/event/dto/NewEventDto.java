package ru.practicum.main_service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.main_service.MainConstants;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class NewEventDto {
    @NotBlank
    @Size(min = MainConstants.MIN_LENGTH_ANNOTATION, max = MainConstants.MAX_LENGTH_ANNOTATION)
    String annotation;

    @NotNull
    Long category;

    @NotBlank
    @Size(min = MainConstants.MIN_LENGTH_DESCRIPTION, max = MainConstants.MAX_LENGTH_DESCRIPTION)
    String description;

    @NotNull
    @JsonFormat(pattern = MainConstants.DT_FORMAT, shape = JsonFormat.Shape.STRING)
    LocalDateTime eventDate;

    @NotNull
    @Valid
    LocationDto location;

    Boolean paid = false;

    @PositiveOrZero
    Integer participantLimit = 0;

    Boolean requestModeration = true;

    @NotBlank
    @Size(min = MainConstants.MIN_LENGTH_TITLE, max = MainConstants.MAX_LENGTH_TITLE)
    String title;
}