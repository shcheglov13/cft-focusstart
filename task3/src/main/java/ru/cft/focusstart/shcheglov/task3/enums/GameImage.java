package ru.cft.focusstart.shcheglov.task3.enums;

import javax.swing.*;

public enum GameImage {
    NUM_1("1.png"),
    NUM_2("2.png"),
    NUM_3("3.png"),
    NUM_4("4.png"),
    NUM_5("5.png"),
    NUM_6("6.png"),
    NUM_7("7.png"),
    NUM_8("8.png"),
    CLOSED("cell.png"),
    MARKED("flag.png"),
    EMPTY("empty.png"),
    BOMB("mine.png"),
    ACTIVE_MINE("active_mine.png"),
    TIMER("timer.png"),
    BOMB_ICON("mineImage.png"),
    APP_ICON("app_icon.png");

    private final String fileName;
    private ImageIcon imageIcon;

    GameImage(String fileName) {
        this.fileName = fileName;
    }

    public synchronized ImageIcon getImageIcon() {
        if (imageIcon == null) {
            imageIcon = new ImageIcon(ClassLoader.getSystemResource("img/" + fileName));
        }

        return imageIcon;
    }
}
