package ru.cft.focusstart.shcheglov.task2.shapes;

public abstract class Shape {
    public static int ROUND_OFF = 2;
    public static String UNIT = "мм";
    public static String UNIT_FOR_AREA = "кв.мм";
    public static String DEGREE = "°";
    public static String CIRCLE_NAME = "CIRCLE";
    public static String RECTANGLE_NAME = "RECTANGLE";
    public static String TRIANGLE_NAME = "TRIANGLE";

    private final String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getPerimeter();

    public abstract double getArea();

    public abstract String getInfo();
}
