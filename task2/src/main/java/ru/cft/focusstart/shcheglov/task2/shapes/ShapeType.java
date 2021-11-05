package ru.cft.focusstart.shcheglov.task2.shapes;

public enum ShapeType {
    CIRCLE(1),
    RECTANGLE(2),
    TRIANGLE(3);

    private final int paramsCount;

    ShapeType(int paramsCount) {
        this.paramsCount = paramsCount;
    }

    public int getParamsCount() {
        return paramsCount;
    }
}
