package ru.cft.focusstart.shcheglov.task1.writers;

import java.io.PrintWriter;

public class ConsoleWriter implements Writer {
    private final PrintWriter writer = new PrintWriter(System.out, true);

    @Override
    public void write(String str) {
        writer.print(str);
    }

    @Override
    public void close() {
        writer.close();
    }
}
