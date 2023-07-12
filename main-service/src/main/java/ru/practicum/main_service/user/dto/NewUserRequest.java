package ru.practicum.main_service.user.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.main_service.MainConstants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NewUserRequest {
    @Email
    @NotBlank
    @Size(min = MainConstants.MIN_LENGTH_FULL_EMAIL, max = MainConstants.MAX_LENGTH_FULL_EMAIL)
    String email;

    @NotBlank
    @Size(min = MainConstants.MIN_LENGTH_USERNAME, max = MainConstants.MAX_LENGTH_USERNAME)
    String name;
}