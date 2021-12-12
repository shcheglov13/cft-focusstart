package ru.cft.focusstart.shcheglov.task3.model;

import ru.cft.focusstart.shcheglov.task3.enums.GameStatus;
import ru.cft.focusstart.shcheglov.task3.enums.GameDifficult;
import ru.cft.focusstart.shcheglov.task3.view.Listeners.ModelListener;

public interface Model {
    Cell[][] getField();

    GameDifficult getDifficult();

    GameStatus getGameStatus();

    void subscribeListener(ModelListener listener);

    void openCell(int x, int y);

    void openCellsAround(int x, int y);

    void setFlag(int x, int y);

    void refresh(GameDifficult difficult);

    int getFlagsCount();
}
