package ru.cft.focusstart.shcheglov.task2.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.shcheglov.task2.App;
import ru.cft.focusstart.shcheglov.task2.reader.TxtFileReader;
import ru.cft.focusstart.shcheglov.task2.shapes.ShapeType;
import ru.cft.focusstart.shcheglov.task2.writers.Writer;
import ru.cft.focusstart.shcheglov.task2.shapes.Circle;
import ru.cft.focusstart.shcheglov.task2.shapes.Rectangle;
import ru.cft.focusstart.shcheglov.task2.shapes.Shape;
import ru.cft.focusstart.shcheglov.task2.shapes.Triangle;

import java.io.IOException;
import java.util.List;

public record Processor(App app, Writer writer) {
    private static final Logger log = LoggerFactory.getLogger(Processor.class.getName());

    public void process() throws IOException {
        if (app.getOutputFilePath() != null) {
            log.debug("Запуск приложения с аргументами: вывод в файл - {}, файл вывода - {}, файл ввода - {}",
                    true, app.getOutputFilePath(), app.getInputFile());
        } else {
            log.debug("Запуск приложения с аргументами: вывод в файл - {}, файл ввода - {}",
                    false, app.getInputFile());
        }

        TxtFileReader parser = new TxtFileReader(app.getInputFile());
        List<String> rawData = parser.getRawData();

        ShapeType shapeType = getShapeTypeFromRawData(rawData);
        double[] shapeDimensions = getShapeDimensionsFromRawData(rawData);

        try {
            Shape shape = getShape(shapeType, shapeDimensions);
            write(shape.getInfo());
        } catch (IllegalArgumentException e) {
            log.error("Ошибка создания фигуры. Причина - {}", e.getMessage());
            log.info("Работа приложения завершена из-за возникшей ошибки");
            System.exit(1);
        }
    }

    private Shape getShape(ShapeType shapeType, double[] dimensions) {
        return switch (shapeType) {
            case CIRCLE -> new Circle(dimensions[0]);
            case RECTANGLE -> new Rectangle(dimensions[0], dimensions[1]);
            case TRIANGLE -> new Triangle(dimensions[0], dimensions[1], dimensions[2]);
        };
    }

    private ShapeType getShapeTypeFromRawData(List<String> rawData) {
        String shapeName = rawData.get(0).trim().toUpperCase();
        return ShapeType.valueOf(shapeName);
    }

    private double[] getShapeDimensionsFromRawData(List<String> rawData) {
        String[] rawDimensions = rawData.get(1).split(" ");
        double[] dimensions = new double[rawDimensions.length];

        try {
            for (int i = 0; i < dimensions.length; i++) {
                dimensions[i] = Double.parseDouble(rawDimensions[i]);
            }
        } catch (NumberFormatException e) {
            log.error("Ошибка конвертации String в double. Причина - {}", e.getMessage());
            log.info("Работа приложения завершена из-за возникшей ошибки");
            System.exit(1);
        }

        return dimensions;
    }

    private void write(String info) throws IOException {
        writer.write(info);
        log.info("информация о фигуре успешно записана");
        writer.close();
    }
}
