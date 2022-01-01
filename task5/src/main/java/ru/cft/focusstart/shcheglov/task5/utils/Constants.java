package ru.cft.focusstart.shcheglov.task5.utils;

import lombok.experimental.UtilityClass;

import java.net.URL;

@UtilityClass
public final class Constants {
    public final static URL PROPERTIES_FILE_URL = Constants.class
            .getClassLoader()
            .getResource("application.properties");

    public final static String PRODUCER_COUNT_PROPERTY = "producersCount";
    public final static String PRODUCER_TIME_PROPERTY = "producersTime";
    public final static String CONSUMER_COUNT_PROPERTY = "consumersCount";
    public final static String CONSUMER_TIME_PROPERTY = "consumersTime";
    public final static String STORAGE_CAPACITY_PROPERTY = "storageSize";
}
