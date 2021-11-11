package reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.cft.focusstart.shcheglov.task2.reader.TxtFileReader;

import javax.naming.OperationNotSupportedException;
import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class TxtFileReaderTest {
    private static final List<String> expected = List.of("circle", "13");
    private static File tempFile;

    @TempDir
    private static Path tempDir;

    @BeforeAll
    public static void init() {
        tempFile = tempDir.resolve("test.txt").toFile();

        try (PrintWriter writer = new PrintWriter(tempFile)) {
            for (String s : expected) {
                writer.println(s);
                writer.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFileTest() throws IOException, OperationNotSupportedException {
        TxtFileReader reader = new TxtFileReader(tempFile);
        Assertions.assertEquals(expected, reader.getRawData());
    }
}
