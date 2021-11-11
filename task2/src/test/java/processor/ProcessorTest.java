package processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.cft.focusstart.shcheglov.task2.App;
import ru.cft.focusstart.shcheglov.task2.processor.Processor;
import ru.cft.focusstart.shcheglov.task2.shapes.Circle;
import ru.cft.focusstart.shcheglov.task2.writers.ConsoleWriter;
import ru.cft.focusstart.shcheglov.task2.writers.FileWriter;

import javax.naming.OperationNotSupportedException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProcessorTest {
    private static final App app = new App();

    @TempDir
    private static Path tempDir;

    @BeforeAll
    public static void init() {
        List<String> dataForWrite = List.of("circle", "13");

        File tempFileIn = tempDir.resolve("test.txt").toFile();
        File tempFileOut = tempDir.resolve("out.txt").toFile();

        app.setInputFile(tempFileIn);
        app.setOutputFilePath(tempFileOut.getAbsolutePath());

        try (PrintWriter writer = new PrintWriter(tempFileIn)) {
            for (String s : dataForWrite) {
                writer.println(s);
                writer.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void consoleOutputTest() throws IOException, OperationNotSupportedException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Processor processor = new Processor(app, new ConsoleWriter());
        processor.process();

        Circle circle = new Circle(13);

        Assertions.assertEquals(circle.getInfo(), output.toString());
    }

    @Test
    public void fileOutputTest() throws IOException, OperationNotSupportedException {
        Processor processor = new Processor(app, new FileWriter(app.getOutputFilePath()));
        processor.process();

        String dataFromOutputFile = new String(Files.readAllBytes(Paths.get(app.getOutputFilePath())));
        Circle circle = new Circle(13);

        Assertions.assertEquals(circle.getInfo(), dataFromOutputFile);
    }
}
