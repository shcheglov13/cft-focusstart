package shapes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.shcheglov.task2.shapes.Circle;

public class CircleTest {
    private static final double EPSILON = 0.0001;
    private static final double RADIUS = 2;

    @Test
    public void negativeParamTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Circle(-RADIUS));
    }

    @Test
    public void perimeterCalculationTest() {
        double expected = Math.PI * 4;
        Assertions.assertEquals(expected, new Circle(RADIUS).getPerimeter(), EPSILON);
    }

    @Test
    public void areaCalculationTest() {
        double expected = Math.PI * 4;
        Assertions.assertEquals(expected, new Circle(RADIUS).getArea(), EPSILON);
    }

    @Test
    public void diameterCalculationTest() {
        double expected = 4;
        Assertions.assertEquals(expected, new Circle(RADIUS).getDiameter(), EPSILON);
    }
}
