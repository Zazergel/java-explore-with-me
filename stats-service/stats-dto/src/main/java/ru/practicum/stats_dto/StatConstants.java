package ru.practicum.stats_dto;

import java.time.format.DateTimeFormatter;

public class StatConstants {
    public static final String DT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern(DT_FORMAT);
    public static final String HIT_ENDPOINT = "/hit";
    public static final String STATS_ENDPOINT = "/stats";

    private StatConstants() {
    }
}