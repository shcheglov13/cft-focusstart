package shapes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.shcheglov.task2.shapes.ShapeType;
import ru.cft.focusstart.shcheglov.task2.shapes.Triangle;

import java.util.Arrays;

public class TriangleTest {
    private static final double NUMERIC_RANGE_START = 0.1;
    private static final double EPSILON = 0.0001;
    private static Triangle triangle;
    private static double sideX;
    private static double sideY;
    private static double sideZ;

    private static void initTriangleParams() {
        double[] randomDoubles = new double[ShapeType.TRIANGLE.getParamsCount()];

        for (int i = 0; i < randomDoubles.length - 1; i++) {
            randomDoubles[i] = Math.random() * Math.pow(2, 10) + NUMERIC_RANGE_START;
        }

        Arrays.sort(randomDoubles);

        sideY = randomDoubles[1];
        sideZ = randomDoubles[2];
        randomDoubles[0] = Math.random() * ((sideZ + sideY) - (sideZ - sideY)) + (sideZ - sideY);
        sideX = randomDoubles[0];
    }

    @BeforeAll
    public static void init() {
        initTriangleParams();
        triangle = new Triangle(sideX, sideY, sideZ);
    }

    @Test
    public void negativeParamTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Triangle(sideX, -sideY, sideZ));
    }

    @Test
    public void sidesConditionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Triangle(sideY + sideZ + 1, sideY, sideZ));
    }

    @Test
    public void perimeterCalculationTest() {
        double expected = sideX + sideY + sideZ;
        Assertions.assertEquals(expected, triangle.getPerimeter(), EPSILON);
    }

    @Test
    public void areaCalculationTest() {
        double semiPerimeter = (sideX + sideY + sideZ) / 2;
        double expected = Math.sqrt(semiPerimeter * (semiPerimeter - sideX) *
                (semiPerimeter - sideY) * (semiPerimeter - sideZ));
        Assertions.assertEquals(expected, triangle.getArea(), EPSILON);
    }

    @Test
    public void anglesCalculationTest() {
        double sizeXAngleExpected = Math.toDegrees(Math.acos(
                ((sideY * sideY) + (sideZ * sideZ) - (sideX * sideX)) / (sideZ * sideY * 2)));
        double sizeYAngleExpected = Math.toDegrees(Math.acos(
                ((sideX * sideX) + (sideY * sideY) - (sideZ * sideZ)) / (sideX * sideY * 2)));
        double sizeZAngleExpected = Math.toDegrees(Math.acos(
                ((sideX * sideX) + (sideZ * sideZ) - (sideY * sideY)) / (sideX * sideZ * 2)));

        Assertions.assertEquals(sizeXAngleExpected, triangle.getSideXAngle(), EPSILON);
        Assertions.assertEquals(sizeYAngleExpected, triangle.getSideYAngle(), EPSILON);
        Assertions.assertEquals(sizeZAngleExpected, triangle.getSideZAngle(), EPSILON);
    }
}
