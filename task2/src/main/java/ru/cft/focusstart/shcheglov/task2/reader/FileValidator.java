package ru.cft.focusstart.shcheglov.task2.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.shcheglov.task2.exceptions.NotValidDataInFileException;
import ru.cft.focusstart.shcheglov.task2.exceptions.ShapeNotSupportedException;
import ru.cft.focusstart.shcheglov.task2.shapes.ShapeType;

import java.util.List;

public class FileValidator {
    private static final Logger log = LoggerFactory.getLogger(FileValidator.class.getName());

    private FileValidator() {
    }

    public static void checkRawData(List<String> rawData) {
        log.info("Начало проверки извлеченных данных: {}", rawData);
        try {
            checkLinesNotBlank(rawData);
            checkLinesCount(rawData);
            checkShapeType(rawData);
            checkParamsCount(rawData);
        } catch (NotValidDataInFileException | ShapeNotSupportedException e) {
            log.error("Ошибка валидации данных. Причина - {}", e.getMessage());
            throw e;
        }
    }

    public static void checkLinesCount(List<String> rawData) {
        if (rawData.size() != TxtFileReader.LINES_FOR_READ) {
            throw new NotValidDataInFileException(String.format(
                    "Количество строк должно равняться %d", TxtFileReader.LINES_FOR_READ)
            );
        }
    }

    public static void checkLinesNotBlank(List<String> rawData) {
        for (String line : rawData) {
            if (line.isBlank()) {
                throw new NotValidDataInFileException("Файл содержит пустые строки");
            }
        }
    }

    public static void checkShapeType(List<String> rawData) {
        String shapeName = rawData.get(0).trim().toUpperCase();
        try {
            ShapeType.valueOf(shapeName);
        } catch (IllegalArgumentException e) {
            throw new ShapeNotSupportedException(String.format("Фигура %s не поддерживается", shapeName));
        }
    }

    public static void checkParamsCount(List<String> rawData) {
        String[] rawParams = rawData.get(1).split(" ");
        String shapeName = rawData.get(0).trim().toUpperCase();
        ShapeType shapeType = ShapeType.valueOf(shapeName);

        if (shapeType.getParamsCount() != rawParams.length) {
            throw new NotValidDataInFileException(String.format(
                    "Количество параметров для фигуры %s должно равняться %d",
                    shapeType, shapeType.getParamsCount())
            );
        }
    }
}
