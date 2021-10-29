package ru.cft.focusstart.shcheglov.task1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите размер таблицы умножения (от 1 до 32)");
            int size = scanner.nextInt();

            while (size < 1 || size > 32) {
                System.out.println("Введите размер от 1 до 32");
                size = scanner.nextInt();
            }

            MultiplicationTable multiplicationTable = new MultiplicationTable(size);
            multiplicationTable.printTable();
        } catch (InputMismatchException e) {
            System.err.print("Введено некорректное значение");
        }
    }
}
