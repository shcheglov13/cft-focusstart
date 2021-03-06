package ru.cft.focusstart.shcheglov.task3.utils;

import lombok.experimental.UtilityClass;
import ru.cft.focusstart.shcheglov.task3.enums.GameDifficult;

import java.net.URL;

@UtilityClass
public final class Constants {
    public static final URL PROPERTY_FILE_URL = Constants.class.getClassLoader().getResource("application.properties");
    public static final GameDifficult DEFAULT_DIFFICULT = GameDifficult.NOVICE;
    public static final String DEFAULT_TIMER_VALUE = "00:00:00";
    public static final String APP_TITLE = "Miner";
    public static final String SETTINGS_TITLE = "Settings";
    public static final String RECORDS_TITLE = "New Record";
    public static final String WIN_TITLE = "Win";
    public static final String LOSE_TITLE = "Lose";
    public static final String HIGH_SCORES_TITLE = "High Scores";
    public static final String GAME_MENU_NAME = "Game";
    public static final String NEW_GAME_MENU_NAME = "New game";
    public static final String EXIT_MENU_NAME = "Exit";
    public static final String LOSE_WINDOW_MESSAGE = "You lose:( Try again!";
    public static final String WIN_WINDOW_MESSAGE = "You win!";
}
