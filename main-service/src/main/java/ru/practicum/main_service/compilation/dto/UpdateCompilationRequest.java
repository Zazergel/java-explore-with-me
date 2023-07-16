package ru.practicum.main_service.compilation.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.main_service.MainConstants;

import javax.validation.constraints.Size;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UpdateCompilationRequest {
    @Size(min = MainConstants.MIN_LENGTH_TITLE, max = MainConstants.MAX_LENGTH_COMPILATION_TITLE)
    String title;
    Boolean pinned;
    List<Long> events;
}
