package ru.cft.focusstart.shcheglov.task3.model;

import ru.cft.focusstart.shcheglov.task3.view.Listeners.ModelListener;
import ru.cft.focusstart.shcheglov.task3.view.Listeners.TimerListener;

public interface GameTimer extends ModelListener {
    void subscribeListener(TimerListener listener);

    void start();

    void stop();

    long getMillis();
}
