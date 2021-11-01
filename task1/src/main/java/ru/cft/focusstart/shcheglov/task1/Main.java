package ru.cft.focusstart.shcheglov.task1;

import ru.cft.focusstart.shcheglov.task1.writers.ConsoleWriter;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final int MIN_SIZE = 1;
    public static final int MAX_SIZE = 32;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.printf("Введите размер таблицы умножения (от %d до %d)%n", MIN_SIZE, MAX_SIZE);
            int size = Integer.parseInt(scanner.nextLine());

            if (size < MIN_SIZE || size > MAX_SIZE) {
                System.err.print("Некорректный размер таблицы умножения");
            } else {
                MultiplicationTableBuilder multiplicationTable = new MultiplicationTableBuilder(size, new ConsoleWriter());
                multiplicationTable.printTable();
            }
        } catch (NumberFormatException e) {
            System.err.print("Введено некорректное значение");
        } catch (IOException e) {
            System.err.print("Ошибка ввода/вывода");
            e.printStackTrace();
        }
    }
}
