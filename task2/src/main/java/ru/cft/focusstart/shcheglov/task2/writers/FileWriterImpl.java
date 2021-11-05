package ru.cft.focusstart.shcheglov.task2.writers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriterImpl implements Writer {
    private final PrintWriter writer;

    public FileWriterImpl(String path) throws FileNotFoundException {
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
