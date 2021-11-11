package ru.cft.focusstart.shcheglov.task2.shapes;

import static ru.cft.focusstart.shcheglov.task2.utils.Constants.*;

public abstract class Shape {
    private final ShapeType type;

    public Shape(ShapeType type) {
        this.type = type;
    }

    public ShapeType getType() {
        return type;
    }

    public abstract double getPerimeter();

    public abstract double getArea();

    public abstract String getInfo();

    public String generateShapeNameInfoString() {
        return String.format("Тип фигуры: %s%n", type);
    }

    public String generateAreaInfoString(double value) {
        return String.format("Площадь: %." + ROUND_OFF + "f " + UNIT_FOR_AREA + "%n", value);
    }

    public String generatePerimeterInfoString(double value) {
        return String.format("Периметр: %." + ROUND_OFF + "f " + UNIT + "%n", value);
    }
}
