package ru.cft.focusstart.shcheglov.task1.writers;

import java.io.Closeable;

public interface Writer extends Closeable {
    void write(String str);
}
