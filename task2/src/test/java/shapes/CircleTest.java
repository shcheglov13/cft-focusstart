package shapes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.shcheglov.task2.shapes.Circle;

public class CircleTest {
    private static final double EPSILON = 0.0001;
    private static Circle circle;
    private static double randomDouble;

    @BeforeAll
    public static void init() {
        randomDouble = Math.random() * Math.pow(2, 10);
        circle = new Circle(randomDouble);
    }

    @Test
    public void negativeParamTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Circle(randomDouble * -1));
    }

    @Test
    public void perimeterCalculationTest() {
        double expected = Math.PI * randomDouble * 2;
        Assertions.assertEquals(expected, circle.getPerimeter(), EPSILON);
    }

    @Test
    public void areaCalculationTest() {
        double expected = Math.PI * randomDouble * randomDouble;
        Assertions.assertEquals(expected, circle.getArea(), EPSILON);
    }
}
