package ru.cft.focusstart.shcheglov.task2.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.shcheglov.task2.exceptions.NotValidDataInFileException;
import ru.cft.focusstart.shcheglov.task2.exceptions.ShapeNotSupportedException;
import ru.cft.focusstart.shcheglov.task2.shapes.ShapeType;

import java.util.List;

public class FileValidator {
    private static final Logger log = LoggerFactory.getLogger(FileValidator.class.getName());

    public static void checkRawData(List<String> rawData) {
        log.info("Запускается проверка файла");
        try {
            checkLinesNotBlank(rawData);
            checkLinesCount(rawData);
            checkShapeType(rawData);
            checkParamsCount(rawData);
        } catch (NotValidDataInFileException | ShapeNotSupportedException e) {
            log.error("Ошибка валидации данных в файле. Причина - {}", e.getMessage());
            log.info("Работа приложения завершена из-за возникшей ошибки");
            System.exit(1);
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
        ShapeType[] types = ShapeType.values();
        String shapeName = rawData.get(0).trim().toUpperCase();

        boolean isNotValid = true;

        for (int i = 0; i < ShapeType.values().length; i++) {
            if (shapeName.equals(types[i].toString())) {
                isNotValid = false;
                break;
            }
        }

        if (isNotValid) {
            throw new ShapeNotSupportedException(String.format("Фигура %s не поддерживается", shapeName));
        }
    }

    public static void checkParamsCount(List<String> rawData) {
        String[] rawParams = rawData.get(1).split(" ");
        String shapeName = rawData.get(0).trim().toUpperCase();

        switch (ShapeType.valueOf(shapeName)) {
            case CIRCLE:
                if (rawParams.length != ShapeType.CIRCLE.getParamsCount()) {
                    throw new NotValidDataInFileException(String.format(
                            "Количество параметров для фигуры %s должно равняться %d",
                            ShapeType.CIRCLE, ShapeType.CIRCLE.getParamsCount())
                    );
                }
                break;
            case RECTANGLE:
                if (rawParams.length != ShapeType.RECTANGLE.getParamsCount()) {
                    throw new NotValidDataInFileException(String.format(
                            "Количество параметров для фигуры %s должно равняться %d",
                            ShapeType.RECTANGLE, ShapeType.RECTANGLE.getParamsCount())
                    );
                }
                break;
            case TRIANGLE:
                if (rawParams.length != ShapeType.TRIANGLE.getParamsCount()) {
                    throw new NotValidDataInFileException(String.format(
                            "Количество параметров для фигуры %s должно равняться %d",
                            ShapeType.TRIANGLE, ShapeType.TRIANGLE.getParamsCount())
                    );
                }
        }
    }
}
