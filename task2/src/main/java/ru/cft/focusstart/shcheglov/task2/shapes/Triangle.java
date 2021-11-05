package ru.cft.focusstart.shcheglov.task2.shapes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Triangle extends Shape {
    private static final Logger log = LoggerFactory.getLogger(Triangle.class.getName());

    private final double sideX;
    private final double sideY;
    private final double sideZ;

    public Triangle(double sideX, double sideY, double sideZ) {
        super(TRIANGLE_NAME);

        if (sideX < 0 || sideY < 0 || sideZ < 0) {
            throw new IllegalArgumentException(String.format("Недопустимые параметры для %s. Одна или несколько сторон " +
                    "имеют отрицательные значения: sideX %f, sideY %f, sideZ %f", TRIANGLE_NAME, sideX, sideY, sideZ));
        }

        if (sideX + sideY < sideZ || sideX + sideZ < sideY || sideY + sideZ < sideX) {
            throw new IllegalArgumentException(String.format("Недопустимые параметры для %s. " +
                    "Нарушено неравенство треугольника", TRIANGLE_NAME));
        }

        this.sideX = sideX;
        this.sideY = sideY;
        this.sideZ = sideZ;

        log.debug("{} успешно создан: sideX {}, sideY {}, sideZ {}", TRIANGLE_NAME, sideX, sideY, sideZ);
    }

    public double getSideX() {
        return sideX;
    }

    public double getSideY() {
        return sideY;
    }

    public double getSideZ() {
        return sideZ;
    }

    @Override
    public double getPerimeter() {
        return sideX + sideY + sideZ;
    }

    @Override
    public double getArea() {
        double semiPerimeter = getPerimeter() / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - sideX) * (semiPerimeter - sideY) * (semiPerimeter - sideZ));
    }

    public double getSideXAngle() {
        return Math.toDegrees(Math.acos(((sideY * sideY) + (sideZ * sideZ) - (sideX * sideX)) / (sideZ * sideY * 2)));
    }

    public double getSideYAngle() {
        return Math.toDegrees(Math.acos(((sideX * sideX) + (sideY * sideY) - (sideZ * sideZ)) / (sideX * sideY * 2)));
    }

    public double getSideZAngle() {
        return Math.toDegrees(Math.acos(((sideX * sideX) + (sideZ * sideZ) - (sideY * sideY)) / (sideX * sideZ * 2)));
    }

    @Override
    public String getInfo() {
        return String.format("Тип фигуры: %s%n" +
                        "Площадь: %." + ROUND_OFF + "f " + UNIT_FOR_AREA + "%n" +
                        "Периметр: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Сторона X: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Противолежащий угол X: %." + ROUND_OFF + "f" + DEGREE + "%n" +
                        "Сторона Y: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Противолежащий угол Y: %." + ROUND_OFF + "f" + DEGREE + "%n" +
                        "Сторона Z: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Противолежащий угол Z: %." + ROUND_OFF + "f" + DEGREE + "%n",
                getName(), getArea(), getPerimeter(), sideX, getSideXAngle(), sideY, getSideYAngle(), sideZ, getSideZAngle()
        );
    }
}