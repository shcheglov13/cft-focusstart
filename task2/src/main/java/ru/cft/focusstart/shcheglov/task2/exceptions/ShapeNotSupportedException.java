package ru.cft.focusstart.shcheglov.task2.exceptions;

public class ShapeNotSupportedException extends RuntimeException {
    public ShapeNotSupportedException() {
    }

    public ShapeNotSupportedException(String message) {
        super(message);
    }
}
