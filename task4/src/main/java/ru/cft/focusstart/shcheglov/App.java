package ru.cft.focusstart.shcheglov;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.shcheglov.exception.WrongNumberException;

import java.math.BigInteger;
import java.util.Scanner;

@Slf4j
public class App {
    private static final int PROCESSORS_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int RANGE_START = 1;

    public static void main(String[] args) {
        log.info("Запуск программы");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите число n:");
            long number = scanner.nextLong();

            CalculationTask[] tasks = createTasks(number);
            Thread[] threads = createThreads(tasks);

            for (Thread thread : threads) {
                thread.start();
                log.info("Поток {} запущен", thread.getName());
            }

            for (Thread thread : threads) {
                thread.join();
            }

            BigInteger result = BigInteger.ZERO;

            for (CalculationTask task : tasks) {
                result = result.add(task.getResult());
            }

            System.out.println("Программа завершила работу. Результат многопоточного вычисления равен: " + result);
        } catch (InterruptedException e) {
            log.error("Вычисление было прервано. Причина: {}", e.getLocalizedMessage());
        } catch (NumberFormatException | WrongNumberException e) {
            log.error("Введено некорректное значение n. Причина: {}", e.getLocalizedMessage());
        }
    }

    public static CalculationTask[] createTasks(long number) {
        CalculationTask[] tasks = new CalculationTask[PROCESSORS_COUNT];

        if (number < tasks.length) {
            throw new WrongNumberException(String.format("Число n меньше количества возможных потоков. n = %d, " +
                    "PROCESSORS_COUNT = %d", number, tasks.length));
        }

        long rangeStart = RANGE_START;
        long iterationNumber = number / tasks.length;
        long rangeEnd = iterationNumber;

        for (int i = 0; i < tasks.length; i++) {
            if (i == tasks.length - 1) {
                long lastIterationNumber = rangeEnd + (number % tasks.length);
                tasks[i] = new CalculationTask(rangeStart, lastIterationNumber);
                break;
            }

            tasks[i] = new CalculationTask(rangeStart, rangeEnd);

            rangeStart = rangeEnd + 1;
            rangeEnd += iterationNumber;
        }

        return tasks;
    }

    public static Thread[] createThreads(CalculationTask[] tasks) {
        Thread[] threads = new Thread[PROCESSORS_COUNT];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(tasks[i]);
            log.info("Создан поток {}", threads[i].getName());
        }

        return threads;
    }
}
