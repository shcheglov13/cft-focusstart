package ru.cft.focusstart.shcheglov.task2.shapes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ru.cft.focusstart.shcheglov.task2.utils.Constants.*;

public class Rectangle extends Shape {
    private static final Logger log = LoggerFactory.getLogger(Rectangle.class.getName());

    private final double width;
    private final double length;

    public Rectangle(double sideA, double sideB) {
        super(ShapeType.RECTANGLE);

        if (sideA <= 0 || sideB <= 0) {
            throw new IllegalArgumentException(String.format("Недопустимые параметры для %s. Одна или несколько сторон " +
                    "имеют значения <= 0: sideA %f, sideB %f", getType(), sideA, sideB));
        }

        this.width = Math.min(sideA, sideB);
        this.length = Math.max(sideA, sideB);

        log.debug("{} успешно создан: width {}, length {}", getType(), width, length);
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getDiagonalLength() {
        return Math.sqrt(length * length + width * width);
    }

    @Override
    public double getPerimeter() {
        return width * 2 + length * 2;
    }

    @Override
    public double getArea() {
        return width * length;
    }

    @Override
    public String getInfo() {
        return String.format(generateShapeNameInfoString() +
                        generateAreaInfoString(getArea()) +
                        generatePerimeterInfoString(getPerimeter()) +
                        "Длина диагонали: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Ширина: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Длина: %." + ROUND_OFF + "f " + UNIT + "%n",
                getDiagonalLength(), width, length
        );
    }
}
