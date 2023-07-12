package ru.practicum.main_service.category.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import ru.practicum.main_service.MainConstants;

import javax.validation.constraints.NotBlank;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NewCategoryDto {
    @NotBlank
    @Length(max = MainConstants.MAX_LENGTH_CATEGORY_NAME)
    String name;
}