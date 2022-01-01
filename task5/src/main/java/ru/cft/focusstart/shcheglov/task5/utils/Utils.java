package ru.cft.focusstart.shcheglov.task5.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.shcheglov.task5.entities.Storage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Slf4j
@UtilityClass
public final class Utils {
    private static long resourceId;
    private static Properties propertiesFile;
    private static Storage storage;

    public synchronized static long getResourceId() {
        resourceId++;
        return resourceId;
    }

    public static Properties getPropertiesFile() throws IOException {
        if (propertiesFile == null) {
            propertiesFile = new Properties();
            if (Constants.PROPERTIES_FILE_URL != null) {
                propertiesFile.load(new FileReader(Constants.PROPERTIES_FILE_URL.getFile(), StandardCharsets.UTF_8));
            } else {
                throw new FileNotFoundException("Файл с конфигурацией не найден");
            }
        }

        return propertiesFile;
    }

    public static int getProducersCount() throws IOException {
        return Integer.parseInt(getPropertiesFile().getProperty(Constants.PRODUCER_COUNT_PROPERTY));
    }

    public static long getProducersTime() throws IOException {
        return Long.parseLong(getPropertiesFile().getProperty(Constants.PRODUCER_TIME_PROPERTY));
    }

    public static int getConsumerCount() throws IOException {
        return Integer.parseInt(getPropertiesFile().getProperty(Constants.CONSUMER_COUNT_PROPERTY));
    }

    public static long getConsumerTime() throws IOException {
        return Long.parseLong(getPropertiesFile().getProperty(Constants.CONSUMER_TIME_PROPERTY));
    }

    public static int getStorageCapacity() throws IOException {
        return Integer.parseInt(getPropertiesFile().getProperty(Constants.STORAGE_CAPACITY_PROPERTY));
    }

    public static Storage getStorage() throws IOException {
        if (storage == null) {
            storage = new Storage(getStorageCapacity());
        }

        return storage;
    }
}
