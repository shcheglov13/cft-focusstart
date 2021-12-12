package ru.cft.focusstart.shcheglov.task3.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    private int bombsAround;
    private boolean isOpen;
    private boolean containsFlag;
    private boolean containsBomb;
    private boolean containsActivatedBomb;

    @Setter(AccessLevel.NONE)
    private int x;
    @Setter(AccessLevel.NONE)
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void incrementBombsAround() {
        bombsAround++;
    }

    public void reset() {
        bombsAround = 0;
        isOpen = false;
        containsFlag = false;
        containsBomb = false;
        containsActivatedBomb = false;
    }
}
