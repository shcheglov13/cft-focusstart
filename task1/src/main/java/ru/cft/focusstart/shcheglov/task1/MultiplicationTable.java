package ru.cft.focusstart.shcheglov.task1;

public class MultiplicationTable {
    public static final String DELIMITER_CHAR_TYPE1 = "|";
    public static final String DELIMITER_CHAR_TYPE2 = "-";
    public static final String DELIMITER_CHAR_TYPE3 = "+";

    private final int size;
    private final Writer writer;

    MultiplicationTable(int size, Writer writer) {
        this.size = size;
        this.writer = writer;
    }

    public void printTable() {
        int sizeDigitsCount = getSizeDigitsCount();
        int maxNumberDigitsCount = getMaxNumberDigitsCount();
        String delimiterLine = getDelimiter(sizeDigitsCount, maxNumberDigitsCount);

        StringBuilder sb = new StringBuilder(getCharactersInTableCount(sizeDigitsCount, maxNumberDigitsCount));
        sb.append(initFirstCell(sizeDigitsCount));

        for (int i = 0; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (i == 0) {
                    sb.append(String.format("%" + (maxNumberDigitsCount) + "d" + DELIMITER_CHAR_TYPE1, j));
                } else {
                    sb.append(String.format("%" + (maxNumberDigitsCount) + "d" + DELIMITER_CHAR_TYPE1, i * j));
                }

                if (j == size) {
                    sb.append(System.lineSeparator());
                }
            }

            sb.append(delimiterLine).append(System.lineSeparator());

            if (i < size) {
                sb.append(String.format("%" + sizeDigitsCount + "d" + DELIMITER_CHAR_TYPE1, i + 1));
            }
        }

        writer.print(sb.toString());
    }

    private int getCharactersInTableCount(int sizeDigitsCount, int maxNumberDigitsCount) {
        int rows = (size + 1) * 2;
        int charsInRow = getCharsInRowCount(sizeDigitsCount, maxNumberDigitsCount);
        int charsInLineSeparators = rows * 2;

        return rows * charsInRow + charsInLineSeparators;
    }

    private int getCharsInRowCount(int sizeDigitsCount, int maxNumberDigitsCount) {
        return sizeDigitsCount + 1 + (maxNumberDigitsCount + 1) * size;
    }

    private int getMaxNumberDigitsCount() {
        return (int) Math.ceil(Math.log10(size * size + 0.5));
    }

    private int getSizeDigitsCount() {
        return (int) Math.ceil(Math.log10(size + 0.5));
    }

    private String initFirstCell(int sizeDigitsCount) {
        return String.format("%" + (sizeDigitsCount + 1) + "s", DELIMITER_CHAR_TYPE1);
    }

    private String getDelimiter(int sizeDigitsCount, int maxNumberDigitsCount) {
        int charsInRow = getCharsInRowCount(sizeDigitsCount, maxNumberDigitsCount);

        StringBuilder sb = new StringBuilder(charsInRow);
        sb.append(DELIMITER_CHAR_TYPE2.repeat(sizeDigitsCount)).append(DELIMITER_CHAR_TYPE3);

        for (int j = 1; j <= size; j++) {
            sb.append(DELIMITER_CHAR_TYPE2.repeat(maxNumberDigitsCount)).append(DELIMITER_CHAR_TYPE3);
        }

        return sb.toString();
    }
}