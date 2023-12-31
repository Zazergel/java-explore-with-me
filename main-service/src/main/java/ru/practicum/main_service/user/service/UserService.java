package ru.practicum.main_service.user.service;


import org.springframework.data.domain.Pageable;
import ru.practicum.main_service.user.User;
import ru.practicum.main_service.user.dto.NewUserRequest;
import ru.practicum.main_service.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(NewUserRequest newUserRequest);

    List<UserDto> getUsers(List<Long> ids, Pageable pageable);

    User getUserById(Long id);

    void deleteById(Long id);
}