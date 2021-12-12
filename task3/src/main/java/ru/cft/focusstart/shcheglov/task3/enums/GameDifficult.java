package ru.cft.focusstart.shcheglov.task3.enums;

import lombok.Getter;

@Getter
public enum GameDifficult {
    NOVICE("Easy", 9, 9, 10),
    MEDIUM("Medium", 16, 16, 40),
    EXPERT("Hard", 16, 30, 99);

    private final String name;
    private final int rows;
    private final int cols;
    private final int bombsCount;

    GameDifficult(String name, int rows, int cols, int bombsCount) {
        this.name = name;
        this.rows = rows;
        this.cols = cols;
        this.bombsCount = bombsCount;
    }
}
