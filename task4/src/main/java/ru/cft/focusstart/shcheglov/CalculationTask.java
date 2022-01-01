package ru.cft.focusstart.shcheglov;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
public class CalculationTask implements Runnable {
    private final long rangeStart;
    private final long rangeEnd;

    @Getter
    private BigInteger result;

    public CalculationTask(long rangeStart, long rangeEnd) {
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
        log.info("Создан Task [{};{}]", rangeStart, rangeEnd);
    }

    @Override
    public void run() {
        result = Function.calculate(rangeStart, rangeEnd);
        log.info("Task [{};{}] завершил вычисления. Результат - {}", rangeStart, rangeEnd, result);
    }
}
