package ru.cft.focusstart.shcheglov.task2.writers;

import java.io.PrintWriter;

public class ConsoleWriter implements SomeWriter {
    private final PrintWriter writer = new PrintWriter(System.out);

    @Override
    public void write(String str) {
        writer.print(str);
        writer.flush();
    }

    @Override
    public void close() {
    }
}
