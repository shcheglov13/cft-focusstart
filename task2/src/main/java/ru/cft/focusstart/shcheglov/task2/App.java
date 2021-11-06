package ru.cft.focusstart.shcheglov.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import ru.cft.focusstart.shcheglov.task2.processor.Processor;
import ru.cft.focusstart.shcheglov.task2.writers.ConsoleWriterImpl;
import ru.cft.focusstart.shcheglov.task2.writers.FileWriterImpl;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(description = "Консольное приложение с объектно-ориентированной архитектурой, выводящее характеристики " +
        "заданной геометрической фигуры",
        mixinStandardHelpOptions = true,
        version = "1.0")
public class App extends ConsoleAttributes implements Callable<Integer> {
    private static final Logger log = LoggerFactory.getLogger(App.class.getName());

    public static void main(String... args) {
        System.exit(runConsole(args));
    }

    private static int runConsole(String... args) {
        return new CommandLine(new App()).execute(args);
    }

    @Override
    public Integer call() {
        Processor processor;

        try {
            processor = new Processor(this,
                    getOutputFilePath() != null ? new FileWriterImpl(getOutputFilePath()) : new ConsoleWriterImpl());
            processor.process();
        } catch (IOException e) {
            log.error("Ошибка ввода/вывода. Причина - {}", e.getMessage());
            log.info("Работа приложения завершена из-за возникшей ошибки");
            return 1;
        }

        return 0;
    }
}
