package shapes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.shcheglov.task2.shapes.Rectangle;

public class RectangleTest {
    private static final double NUMERIC_RANGE_START = 0.1;
    private static final double EPSILON = 0.0001;
    private static Rectangle rectangle;
    private static double width;
    private static double length;

    @BeforeAll
    public static void init() {
        double sideA = Math.random() * Math.pow(2, 10) + NUMERIC_RANGE_START;
        double sideB = Math.random() * Math.pow(2, 10) + NUMERIC_RANGE_START;

        width = Math.min(sideA, sideB);
        length = Math.max(sideA, sideB);
        rectangle = new Rectangle(width, length);
    }

    @Test
    public void negativeParamTestTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rectangle(width, -length));
    }

    @Test
    public void perimeterCalculationTest() {
        double expected = width + width + length + length;
        Assertions.assertEquals(expected, rectangle.getPerimeter(), EPSILON);
    }

    @Test
    public void areaCalculationTest() {
        double expected = width * length;
        Assertions.assertEquals(expected, rectangle.getArea(), EPSILON);
    }

    @Test
    public void diagonalCalculationTest() {
        double expected = Math.sqrt(length * length + width * width);
        Assertions.assertEquals(expected, rectangle.getDiagonalLength(), EPSILON);
    }
}
