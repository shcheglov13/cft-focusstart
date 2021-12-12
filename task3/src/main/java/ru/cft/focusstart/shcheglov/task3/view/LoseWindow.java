package ru.cft.focusstart.shcheglov.task3.view;

import ru.cft.focusstart.shcheglov.task3.utils.Constants;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoseWindow extends GameResultWindow {
    public LoseWindow(JFrame owner, ActionListener newGameListener, ActionListener exitListener) {
        super(owner, newGameListener, exitListener, Constants.LOSE_TITLE, Constants.LOSE_WINDOW_MESSAGE);
    }
}
