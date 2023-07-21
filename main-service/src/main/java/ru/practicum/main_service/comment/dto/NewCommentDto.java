package ru.practicum.main_service.comment.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.main_service.MainConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NewCommentDto {
    @NotBlank
    @Size(min = MainConstants.MIN_LENGTH_COMMENT_TEXT, max = MainConstants.MAX_LENGTH_COMMENT_TEXT)
    String text;
}