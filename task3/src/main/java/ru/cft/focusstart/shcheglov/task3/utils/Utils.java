package ru.cft.focusstart.shcheglov.task3.utils;

import lombok.experimental.UtilityClass;

import java.sql.Time;

@UtilityClass
public final class Utils {
    public static Time convertMillisToTime(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = ((millis / (1000 * 60)) % 60);
        long hours = ((millis / (1000 * 60 * 60)) % 24);

        return Time.valueOf(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
}
