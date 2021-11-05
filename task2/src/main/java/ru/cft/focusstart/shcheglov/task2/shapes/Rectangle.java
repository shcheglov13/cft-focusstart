package ru.cft.focusstart.shcheglov.task2.shapes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rectangle extends Shape {
    private static final Logger log = LoggerFactory.getLogger(Rectangle.class.getName());

    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        super(RECTANGLE_NAME);

        if (width < 0 || height < 0) {
            throw new IllegalArgumentException(String.format("Недопустимые параметры для %s. Одна или несколько сторон " +
                    "имеют отрицательные значения: width %f, height %f", RECTANGLE_NAME, width, height));
        }

        this.width = width;
        this.height = height;

        log.debug("{} успешно создан: width {}, height {}", RECTANGLE_NAME, width, height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDiagonalLength() {
        return Math.sqrt(height * height + width * width);
    }

    @Override
    public double getPerimeter() {
        return width * 2 + height * 2;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String getInfo() {
        return String.format("Тип фигуры: %s%n" +
                        "Площадь: %." + ROUND_OFF + "f " + UNIT_FOR_AREA + "%n" +
                        "Периметр: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Длина диагонали: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Ширина: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Высота: %." + ROUND_OFF + "f " + UNIT + "%n",
                getName(), getArea(), getPerimeter(), getDiagonalLength(), width, height
        );
    }
}
