package ru.cft.focusstart.shcheglov.task2.shapes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ru.cft.focusstart.shcheglov.task2.utils.Constants.*;

public class Triangle extends Shape {
    private static final Logger log = LoggerFactory.getLogger(Triangle.class.getName());

    private final double sideX;
    private final double sideY;
    private final double sideZ;

    public Triangle(double sideX, double sideY, double sideZ) {
        super(ShapeType.TRIANGLE);

        if (sideX <= 0 || sideY <= 0 || sideZ <= 0) {
            throw new IllegalArgumentException(String.format("Недопустимые параметры для %s. Одна или несколько сторон " +
                    "имеют значения <= 0: sideX %f, sideY %f, sideZ %f", getType(), sideX, sideY, sideZ));
        }

        if (sideX + sideY <= sideZ || sideX + sideZ <= sideY || sideY + sideZ <= sideX) {
            throw new IllegalArgumentException(String.format("Недопустимые параметры для %s. " +
                    "Нарушено неравенство треугольника", getType()));
        }

        this.sideX = sideX;
        this.sideY = sideY;
        this.sideZ = sideZ;

        log.debug("{} успешно создан: sideX {}, sideY {}, sideZ {}", getType(), sideX, sideY, sideZ);
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
        return calculateAngle(sideZ, sideY, sideX);
    }

    public double getSideYAngle() {
        return calculateAngle(sideX, sideY, sideZ);
    }

    public double getSideZAngle() {
        return calculateAngle(sideX, sideZ, sideY);
    }

    private double calculateAngle(double sideA, double sideB, double sideC) {
        return Math.toDegrees(Math.acos(((sideA * sideA) + (sideB * sideB) - (sideC * sideC)) / (sideA * sideB * 2)));
    }

    @Override
    public String getInfo() {
        return String.format(super.getInfo() +
                        "Сторона X: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Противолежащий угол X: %." + ROUND_OFF + "f" + DEGREE + "%n" +
                        "Сторона Y: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Противолежащий угол Y: %." + ROUND_OFF + "f" + DEGREE + "%n" +
                        "Сторона Z: %." + ROUND_OFF + "f " + UNIT + "%n" +
                        "Противолежащий угол Z: %." + ROUND_OFF + "f" + DEGREE + "%n",
                sideX, getSideXAngle(), sideY, getSideYAngle(), sideZ, getSideZAngle()
        );
    }
}
