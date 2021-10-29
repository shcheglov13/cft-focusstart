package ru.cft.focusstart.shcheglov.task1;

public class MultiplicationTable {
    private final int size;

    MultiplicationTable(int size) {
        this.size = size;
    }

    public void printTable() {
        int maxNumberDigitsCount = getMaxNumberDigitsCount();
        int sizeDigitsCount = getSizeDigitsCount();

        StringBuilder sb = new StringBuilder(getCharactersInTableCount(sizeDigitsCount, maxNumberDigitsCount));
        sb.append(String.format("%" + (sizeDigitsCount + 1) + "s", "|"));

        for (int i = 0; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (i == 0) {
                    sb.append(String.format("%" + (maxNumberDigitsCount + 1) + "d|", j));
                } else {
                    sb.append(String.format("%" + (maxNumberDigitsCount + 1) + "d|", i * j));
                }

                if (j == size) {
                    sb.append(System.lineSeparator());
                }
            }

            sb.append("-".repeat(sizeDigitsCount)).append("+");

            for (int j = 1; j <= size; j++) {
                sb.append("-".repeat(maxNumberDigitsCount + 1)).append("+");

                if (j == size) {
                    sb.append(System.lineSeparator());
                }
            }

            if (i < size) {
                sb.append(String.format("%" + sizeDigitsCount + "d|", i + 1));
            }
        }

        System.out.print(sb);
    }

    private int getCharactersInTableCount(int sizeDigitsCount, int maxNumberDigitsCount) {
        int rows = (size + 1) * 2;
        int charsInRow = sizeDigitsCount + 1 + (maxNumberDigitsCount + 2) * size;
        int charsInLineSeparators = rows * 2;

        return rows * charsInRow + charsInLineSeparators;
    }

    private int getMaxNumberDigitsCount() {
        return (int) Math.ceil(Math.log10(size * size + 0.5));
    }

    private int getSizeDigitsCount() {
        return (int) Math.ceil(Math.log10(size + 0.5));
    }
}
