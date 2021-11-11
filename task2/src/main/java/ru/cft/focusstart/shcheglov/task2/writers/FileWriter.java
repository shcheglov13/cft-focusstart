package ru.cft.focusstart.shcheglov.task2.writers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriter implements SomeWriter {
    private final PrintWriter writer;

    public FileWriter(String path) throws FileNotFoundException {
        writer = new PrintWriter(path);
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
