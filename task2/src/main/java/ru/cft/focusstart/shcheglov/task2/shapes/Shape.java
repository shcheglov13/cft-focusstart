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

    public String getInfo() {
        return String.format("Тип фигуры: %s%n" +
                        "Площадь: %." + ROUND_OFF + "f " + UNIT_FOR_AREA + "%n" +
                        "Периметр: %." + ROUND_OFF + "f " + UNIT + "%n",
                type, getArea(), getPerimeter());
    }
}
