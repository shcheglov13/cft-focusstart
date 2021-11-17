package ru.cft.focusstart.shcheglov.task2.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.shcheglov.task2.utils.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtFileReader {
    private static final Logger log = LoggerFactory.getLogger(TxtFileReader.class.getName());
    public static final int LINES_FOR_READ = 2;

    private final File inputFile;

    public TxtFileReader(File inputFile) {
        this.inputFile = inputFile;
    }

    public List<String> getRawData() throws IOException {
        log.info("Начало парсинга файла: {}", inputFile);
        List<String> rawData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile, Constants.CHARSET))) {
            for (int i = 0; i < LINES_FOR_READ; i++) {
                if (reader.ready()) {
                    rawData.add(reader.readLine());
                }
            }
        }

        FileValidator.checkRawData(rawData);
        log.info("Данные успешно извлечены из файла: {}", inputFile);

        return rawData;
    }
}
