package shapes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.shcheglov.task2.shapes.Rectangle;

public class RectangleTest {
    private static final double EPSILON = 0.0001;
    private static final double WIDTH = 1;
    private static final double LENGTH = 2;

    @Test
    public void negativeParamTestTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rectangle(WIDTH, -LENGTH));
    }

    @Test
    public void perimeterCalculationTest() {
        double expected = 6;
        Assertions.assertEquals(expected, new Rectangle(WIDTH, LENGTH).getPerimeter(), EPSILON);
    }

    @Test
    public void areaCalculationTest() {
        double expected = 2;
        Assertions.assertEquals(expected, new Rectangle(WIDTH, LENGTH).getArea(), EPSILON);
    }

    @Test
    public void diagonalCalculationTest() {
        double expected = Math.sqrt(5);
        Assertions.assertEquals(expected, new Rectangle(WIDTH, LENGTH).getDiagonalLength(), EPSILON);
    }
}
