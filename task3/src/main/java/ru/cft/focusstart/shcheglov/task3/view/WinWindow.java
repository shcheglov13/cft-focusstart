package ru.cft.focusstart.shcheglov.task3.view;

import ru.cft.focusstart.shcheglov.task3.utils.Constants;

import javax.swing.*;
import java.awt.event.ActionListener;

public class WinWindow extends GameResultWindow {
    public WinWindow(JFrame owner, ActionListener newGameListener, ActionListener exitListener) {
        super(owner, newGameListener, exitListener, Constants.WIN_TITLE, Constants.WIN_WINDOW_MESSAGE);
    }
}
