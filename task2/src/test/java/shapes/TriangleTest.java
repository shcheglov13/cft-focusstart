package shapes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.shcheglov.task2.shapes.Triangle;

public class TriangleTest {
    private static final double EPSILON = 0.0001;
    private static final double SIDE = 3;

    @Test
    public void negativeParamTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Triangle(SIDE, -SIDE, SIDE));
    }

    @Test
    public void sidesConditionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Triangle(SIDE + SIDE, SIDE, SIDE));
    }

    @Test
    public void perimeterCalculationTest() {
        double expected = 9;
        Assertions.assertEquals(expected, new Triangle(SIDE, SIDE, SIDE).getPerimeter(), EPSILON);
    }

    @Test
    public void areaCalculationTest() {
        double expected = 6;
        Assertions.assertEquals(expected, new Triangle(SIDE + 1, SIDE + 2, SIDE).getArea(), EPSILON);
    }

    @Test
    public void anglesCalculationTest() {
        double expected = 60;
        Triangle triangle = new Triangle(SIDE, SIDE, SIDE);

        Assertions.assertEquals(expected, triangle.getSideXAngle(), EPSILON);
        Assertions.assertEquals(expected, triangle.getSideYAngle(), EPSILON);
        Assertions.assertEquals(expected, triangle.getSideZAngle(), EPSILON);
    }
}
