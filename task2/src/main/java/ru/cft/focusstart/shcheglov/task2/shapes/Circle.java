package ru.cft.focusstart.shcheglov.task2.shapes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Circle extends Shape {
    private static final Logger log = LoggerFactory.getLogger(Circle.class.getName());
    private final double radius;

    public Circle(double radius) {
        super(CIRCLE_NAME);

        if (radius < 0) {
            throw new IllegalArgumentException(String.format("Недопустимые параметры для %s. " +
                    "Радиус имеет отрицательное значение: radius - %s", CIRCLE_NAME, radius));
        }

        this.radius = radius;
        log.debug("{} успешно создан: radius {}", CIRCLE_NAME, radius);
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return radius * radius;
    }

    @Override
    public double getPerimeter() {
        return Math.PI * radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String getInfo() {
        return String.format("Тип фигуры: %s%n" +
                        "Площадь: %." + ROUND_OFF + "f " + UNIT_FOR_AREA + "%n" +
                        "Периметр: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Диаметр: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Радиус: %." + ROUND_OFF + "f " + UNIT + "%n",
                getName(), getArea(), getPerimeter(), getDiameter(), radius
        );
    }
}
