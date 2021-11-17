package ru.cft.focusstart.shcheglov.task2.writers;

import ru.cft.focusstart.shcheglov.task2.utils.Constants;

import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter implements SomeWriter {
    private final PrintWriter writer;

    public FileWriter(String path) throws IOException {
        writer = new PrintWriter(path, Constants.CHARSET);
    }

    @Override
    public void write(String str) {
        writer.print(str);
        writer.flush();
    }

    @Override
    public void close() {
        writer.close();
    }
}
