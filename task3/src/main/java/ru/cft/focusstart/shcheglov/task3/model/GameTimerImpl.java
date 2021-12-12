package ru.cft.focusstart.shcheglov.task3.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.shcheglov.task3.enums.GameStatus;
import ru.cft.focusstart.shcheglov.task3.view.Listeners.TimerListener;

import java.time.Instant;
import java.util.*;

@Slf4j
public class GameTimerImpl implements GameTimer {
    private final List<TimerListener> listeners;
    private Timer timer;
    private long startTime;
    private boolean isStarted;

    @Getter
    private long millis;

    public GameTimerImpl(Model model) {
        listeners = new ArrayList<>();
        model.subscribeListener(this);
        log.info("Таймер инициализирован");
    }

    @Override
    public void start() {
        startTime = Instant.now().toEpochMilli();
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                millis = Instant.now().toEpochMilli() - startTime;
                notifyListeners();
            }
        }, 0, 1000);

        log.info("Таймер запущен");
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
        isStarted = false;
    }

    @Override
    public void update(Model model) {
        GameStatus status = model.getGameStatus();

        if (status == GameStatus.IN_PROGRESS && !isStarted) {
            start();
            isStarted = true;
        } else if (status == GameStatus.WIN || status == GameStatus.LOSE || (status == GameStatus.RESTARTED && isStarted)) {
            stop();
        }
    }

    @Override
    public void subscribeListener(TimerListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (TimerListener listener : listeners) {
            listener.updateTime(this);
        }
    }
}
