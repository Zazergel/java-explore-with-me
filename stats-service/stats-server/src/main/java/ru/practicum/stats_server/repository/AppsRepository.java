package ru.practicum.stats_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.stats_server.model.App;

import java.util.Optional;

public interface AppsRepository extends JpaRepository<App, Long> {

    Optional<App> getAppByName(String name);
}