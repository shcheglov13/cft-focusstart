package ru.cft.focusstart.shcheglov.task1;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static final int MIN_SIZE = 1;
    public static final int MAX_SIZE = 32;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите размер таблицы умножения (от 1 до 32)");
            int size = Integer.parseInt(scanner.nextLine());

            if (size < MIN_SIZE || size > MAX_SIZE) {
                System.err.print("Некорректный размер таблицы умножения");
            } else {
                MultiplicationTable multiplicationTable = new MultiplicationTable(size, str -> {
                    PrintWriter writer = new PrintWriter(System.out, true);
                    writer.print(str);
                    writer.close();
                });

                multiplicationTable.printTable();
            }
        } catch (NumberFormatException e) {
            System.err.print("Введено некорректное значение");
        }
    }
}
