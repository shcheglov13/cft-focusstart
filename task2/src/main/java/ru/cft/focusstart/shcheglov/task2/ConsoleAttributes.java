package ru.cft.focusstart.shcheglov.task2;

import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;

public class ConsoleAttributes {
    public static final String FILE_OUTPUT = "-f";

    @Parameters(paramLabel = "InputFile path", description = "Путь до файла с кодом фигуры и ее параметрами")
    private File inputFile;

    @Option(names = FILE_OUTPUT, description = "Опция для записи в файл. Если указана, то требуется прописать путь " +
            "до файла, в который будут записаны данные")
    private String outputFilePath;

    public File getInputFile() {
        return inputFile;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFilePath(String path) {
        this.outputFilePath = path;
    }
}
