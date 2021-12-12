package ru.cft.focusstart.shcheglov.task3.controller;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.shcheglov.task3.db.Database;
import ru.cft.focusstart.shcheglov.task3.enums.GameDifficult;
import ru.cft.focusstart.shcheglov.task3.model.GameTimer;
import ru.cft.focusstart.shcheglov.task3.model.Model;
import ru.cft.focusstart.shcheglov.task3.view.Listeners.ModelListener;
import ru.cft.focusstart.shcheglov.task3.view.Listeners.TimerListener;

import java.sql.Time;
import java.util.Optional;

@Slf4j
public class ControllerImpl implements Controller {
    private final Model model;
    private final GameTimer timer;

    public ControllerImpl(Model model, GameTimer timer) {
        this.model = model;
        this.timer = timer;
        log.info("Контроллер инициализирован");
    }

    @Override
    public void openCell(int x, int y) {
        model.openCell(x, y);
    }

    @Override
    public void setFlag(int x, int y) {
        model.setFlag(x, y);
    }

    @Override
    public void openCellsAround(int x, int y) {
        model.openCellsAround(x, y);
    }

    @Override
    public void restart(GameDifficult difficult) {
        model.refresh(difficult);
        log.info("Игра перезапущена. Сложность: {}", difficult.getName());
    }

    @Override
    public void subscribeToModel(ModelListener listener) {
        model.subscribeListener(listener);
    }

    @Override
    public void subscribeToTimer(TimerListener listener) {
        timer.subscribeListener(listener);
    }

    @Override
    public long getCurrentMillis() {
        return timer.getMillis();
    }

    @Override
    public void insertRecordIntoDatabase(String name, Time time, GameDifficult difficult) {
        Database.insertRecordIntoDatabase(name, time, difficult.ordinal() + 1);
    }

    @Override
    public String[] getBestRecord(GameDifficult difficult) {
        Optional<String[]> data = Database.getBestRecord(difficult.ordinal() + 1);
        return data.orElseGet(() -> new String[0]);
    }
}
