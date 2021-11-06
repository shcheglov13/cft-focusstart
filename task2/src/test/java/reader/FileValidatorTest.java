package reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.shcheglov.task2.exceptions.NotValidDataInFileException;
import ru.cft.focusstart.shcheglov.task2.exceptions.ShapeNotSupportedException;
import ru.cft.focusstart.shcheglov.task2.reader.FileValidator;

import java.util.List;

public class FileValidatorTest {
    private static List<String> rawDataWithBlankLine;
    private static List<String> rawDataWithUnsupportedShape;
    private static List<String> rawDataWithWrongLinesCount;
    private static List<String> rawDataWithWrongParamsCount;

    @BeforeAll
    public static void init() {
        rawDataWithBlankLine = List.of("CIRCLE", "  ");
        rawDataWithUnsupportedShape = List.of("SQUARE", "5");
        rawDataWithWrongLinesCount = List.of("TRIANGLE");
        rawDataWithWrongParamsCount = List.of("TRIANGLE", "5 7 8 8");
    }

    @Test
    public void blankLineTest() {
        Assertions.assertThrows(NotValidDataInFileException.class, () -> FileValidator.checkLinesNotBlank(rawDataWithBlankLine));
    }

    @Test
    public void unsupportedShapeTest() {
        Assertions.assertThrows(ShapeNotSupportedException.class, () -> FileValidator.checkShapeType(rawDataWithUnsupportedShape));
    }

    @Test
    public void wrongLinesCountTest() {
        Assertions.assertThrows(NotValidDataInFileException.class, () -> FileValidator.checkLinesCount(rawDataWithWrongLinesCount));
    }

    @Test
    public void wrongParamsCountTest() {
        Assertions.assertThrows(NotValidDataInFileException.class, () -> FileValidator.checkParamsCount(rawDataWithWrongParamsCount));
    }
}
