package ru.cft.focusstart.shcheglov.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import ru.cft.focusstart.shcheglov.task2.exceptions.NotValidDataInFileException;
import ru.cft.focusstart.shcheglov.task2.exceptions.ShapeNotSupportedException;
import ru.cft.focusstart.shcheglov.task2.processor.Processor;
import ru.cft.focusstart.shcheglov.task2.writers.ConsoleWriter;
import ru.cft.focusstart.shcheglov.task2.writers.SomeWriter;
import ru.cft.focusstart.shcheglov.task2.writers.FileWriter;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;

@Command(description = "Консольное приложение с объектно-ориентированной архитектурой, выводящее характеристики " +
        "заданной геометрической фигуры",
        mixinStandardHelpOptions = true,
        version = "1.0")
public class App extends ConsoleAttributes implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(App.class.getName());

    public static void main(String... args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public void run() {
        try (SomeWriter writer = getOutputFilePath() != null ?
                new FileWriter(getOutputFilePath()) : new ConsoleWriter()) {
            Processor processor = new Processor(this, writer);
            processor.process();
        } catch (IOException e) {
            log.error("Ошибка ввода/вывода. Причина - {}", e.getMessage());
            log.info("Работа приложения завершена из-за возникшей ошибки");
        } catch (NotValidDataInFileException | ShapeNotSupportedException | OperationNotSupportedException
                | IllegalArgumentException e) {
            log.info("Работа приложения завершена из-за возникшей ошибки");
        }
    }
}
