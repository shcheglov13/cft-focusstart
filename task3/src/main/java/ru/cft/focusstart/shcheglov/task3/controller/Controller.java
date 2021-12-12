package ru.cft.focusstart.shcheglov.task3.controller;

import ru.cft.focusstart.shcheglov.task3.enums.GameDifficult;
import ru.cft.focusstart.shcheglov.task3.view.Listeners.ModelListener;
import ru.cft.focusstart.shcheglov.task3.view.Listeners.TimerListener;

import java.sql.Time;

public interface Controller {
    void restart(GameDifficult difficult);

    void subscribeToModel(ModelListener listener);

    void subscribeToTimer(TimerListener listener);

    long getCurrentMillis();

    void openCell(int x, int y);

    void setFlag(int x, int y);

    void openCellsAround(int x, int y);

    void insertRecordIntoDatabase(String name, Time time, GameDifficult difficult);

    String[] getBestRecord(GameDifficult difficult);
}
