package ru.cft.focusstart.shcheglov.task2.writers;

import java.io.Closeable;

public interface SomeWriter extends Closeable {
    void write(String str);
}
