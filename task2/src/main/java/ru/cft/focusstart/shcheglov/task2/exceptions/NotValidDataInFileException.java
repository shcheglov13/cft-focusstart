package ru.cft.focusstart.shcheglov.task2.exceptions;

public class NotValidDataInFileException extends RuntimeException {
    public NotValidDataInFileException() {
    }

    public NotValidDataInFileException(String message) {
        super(message);
    }
}
