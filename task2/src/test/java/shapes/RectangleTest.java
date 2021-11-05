package shapes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.shcheglov.task2.shapes.Rectangle;

public class RectangleTest {
    private static final double EPSILON = 0.0001;
    private static Rectangle rectangle;
    private static double width;
    private static double height;

    @BeforeAll
    public static void init() {
        width = Math.random() * Math.pow(2, 10);
        height = Math.random() * Math.pow(2, 10);
        rectangle = new Rectangle(width, height);
    }

    @Test
    public void negativeParamTestTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rectangle(width, -height));
    }

    @Test
    public void perimeterCalculationTest() {
        double expected = width + width + height + height;
        Assertions.assertEquals(expected, rectangle.getPerimeter(), EPSILON);
    }

    @Test
    public void areaCalculationTest() {
        double expected = width * height;
        Assertions.assertEquals(expected, rectangle.getArea(), EPSILON);
    }

    @Test
    public void diagonalCalculationTest() {
        double expected = Math.sqrt(height * height + width * width);
        Assertions.assertEquals(expected, rectangle.getDiagonalLength(), EPSILON);
    }
}
