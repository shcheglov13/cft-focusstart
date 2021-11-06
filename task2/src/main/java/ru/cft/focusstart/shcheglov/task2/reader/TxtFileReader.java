package ru.cft.focusstart.shcheglov.task2.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtFileReader {
    private static final Logger log = LoggerFactory.getLogger(TxtFileReader.class.getName());
    public static final int LINES_FOR_READ = 2;

    private final BufferedReader reader;

    public TxtFileReader(File inputFile) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(inputFile));
    }

    public List<String> getRawData() throws IOException {
        log.info("Начало парсинга файла");
        List<String> rawData = new ArrayList<>();

        for (int i = 0; i < LINES_FOR_READ; i++) {
            if (reader.ready()) {
                rawData.add(reader.readLine());
            }
        }

        FileValidator.checkRawData(rawData);
        log.info("Данные успешно извлечены из файла");

        return rawData;
    }
}
