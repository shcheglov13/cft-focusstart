package ru.cft.focusstart.shcheglov.task2.shapes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ru.cft.focusstart.shcheglov.task2.utils.Constants.*;

public class Circle extends Shape {
    private static final Logger log = LoggerFactory.getLogger(Circle.class.getName());
    private final double radius;

    public Circle(double radius) {
        super(ShapeType.CIRCLE);

        if (radius <= 0) {
            throw new IllegalArgumentException(String.format("Недопустимые параметры для %s. " +
                    "Радиус имеет значение <= 0: radius - %s", getType(), radius));
        }

        this.radius = radius;
        log.debug("{} успешно создан: radius {}", getType(), radius);
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return radius * 2;
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
        return String.format(super.getInfo() +
                        "Диаметр: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Радиус: %." + ROUND_OFF + "f " + UNIT + "%n",
                getDiameter(), radius
        );
    }
}
