package ru.cft.focusstart.shcheglov;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
@UtilityClass
public final class Function {
    public static BigInteger calculate(long rangeStart, long rangeEnd) {
        BigInteger result = BigInteger.ZERO;

        for (long i = rangeStart; i <= rangeEnd; i++) {
            result = result.add(BigInteger.valueOf((long) Math.pow(i, 2)));
        }

        return result;
    }
}
