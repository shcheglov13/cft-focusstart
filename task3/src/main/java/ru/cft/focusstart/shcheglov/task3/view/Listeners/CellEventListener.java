package ru.cft.focusstart.shcheglov.task3.view.Listeners;

import ru.cft.focusstart.shcheglov.task3.enums.ButtonType;

public interface CellEventListener {
    void onMouseClick(int x, int y, ButtonType buttonType);
}
