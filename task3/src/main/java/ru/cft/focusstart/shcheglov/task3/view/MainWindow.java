package ru.cft.focusstart.shcheglov.task3.view;

import ru.cft.focusstart.shcheglov.task3.model.GameTimer;
import ru.cft.focusstart.shcheglov.task3.utils.Utils;
import ru.cft.focusstart.shcheglov.task3.view.Listeners.CellEventListener;
import ru.cft.focusstart.shcheglov.task3.controller.Controller;
import ru.cft.focusstart.shcheglov.task3.utils.Constants;
import ru.cft.focusstart.shcheglov.task3.enums.ButtonType;
import ru.cft.focusstart.shcheglov.task3.enums.GameImage;
import ru.cft.focusstart.shcheglov.task3.enums.GameStatus;
import ru.cft.focusstart.shcheglov.task3.enums.GameDifficult;
import ru.cft.focusstart.shcheglov.task3.model.Cell;
import ru.cft.focusstart.shcheglov.task3.model.Model;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.shcheglov.task3.view.Listeners.ModelListener;
import ru.cft.focusstart.shcheglov.task3.view.Listeners.TimerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;

@Slf4j
public class MainWindow extends JFrame implements ModelListener, TimerListener {
    private final Controller controller;
    private GameDifficult difficult;

    private final Container contentPane;
    private final GridBagLayout mainLayout;

    private JMenuItem newGameMenu;
    private JMenuItem highScoresMenu;
    private JMenuItem settingsMenu;
    private JMenuItem exitMenu;

    private CellEventListener listener;

    private JButton[][] cellButtons;
    private JLabel timerLabel;
    private JLabel bombsCounterLabel;

    public MainWindow(Controller controller) {
        super(Constants.APP_TITLE);

        this.controller = controller;

        difficult = Constants.DEFAULT_DIFFICULT;
        contentPane = getContentPane();
        mainLayout = new GridBagLayout();
        contentPane.setLayout(mainLayout);
        contentPane.setBackground(new Color(144, 158, 184));

        createMenu();
        createGameField(difficult.getRows(), difficult.getCols());

        SettingsWindow settingsWindow = new SettingsWindow(this);
        HighScoresWindow highScoresWindow = new HighScoresWindow(this);

        setNewGameMenuAction(e -> {
            restart(difficult);
            createGameField(difficult.getRows(), difficult.getCols());
        });
        setHighScoresMenuAction(e -> {
            highScoresWindow.setRecord(GameDifficult.NOVICE, controller.getBestRecord(GameDifficult.NOVICE));
            highScoresWindow.setRecord(GameDifficult.MEDIUM, controller.getBestRecord(GameDifficult.MEDIUM));
            highScoresWindow.setRecord(GameDifficult.EXPERT, controller.getBestRecord(GameDifficult.EXPERT));
            highScoresWindow.setVisible(true);
        });
        setExitMenuAction(e -> dispose());
        setSettingsMenuAction(e -> {
            settingsWindow.setGameTypeListener(gameType -> {
                switch (gameType) {
                    case NOVICE -> restart(GameDifficult.NOVICE);
                    case MEDIUM -> restart(GameDifficult.MEDIUM);
                    case EXPERT -> restart(GameDifficult.EXPERT);
                }
            });
            settingsWindow.setVisible(true);
        });
        setCellListener((x, y, buttonType) -> {
            switch (buttonType) {
                case LEFT_BUTTON -> controller.openCell(x, y);
                case RIGHT_BUTTON -> controller.setFlag(x, y);
                case MIDDLE_BUTTON -> controller.openCellsAround(x, y);
            }
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(GameImage.APP_ICON.getImageIcon().getImage());
        setResizable(false);
        setVisible(true);
        controller.subscribeToModel(this);
        controller.subscribeToTimer(this);

        log.info("View инициализировано");
    }

    private void restart(GameDifficult difficult) {
        this.difficult = difficult;
        controller.restart(difficult);
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu(Constants.GAME_MENU_NAME);

        gameMenu.add(newGameMenu = new JMenuItem(Constants.NEW_GAME_MENU_NAME));
        gameMenu.addSeparator();
        gameMenu.add(highScoresMenu = new JMenuItem(Constants.HIGH_SCORES_TITLE));
        gameMenu.add(settingsMenu = new JMenuItem(Constants.SETTINGS_TITLE));
        gameMenu.addSeparator();
        gameMenu.add(exitMenu = new JMenuItem(Constants.EXIT_MENU_NAME));

        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
    }

    private void createGameField(int rowsCount, int colsCount) {
        contentPane.removeAll();
        setPreferredSize(new Dimension(25 * colsCount + 70, 25 * rowsCount + 110));

        addButtonsPanel(createButtonsPanel(rowsCount, colsCount));
        addTimerImage();
        addTimerLabel(timerLabel = new JLabel());
        addBombCounter(bombsCounterLabel = new JLabel());
        setTimerValue(Time.valueOf(Constants.DEFAULT_TIMER_VALUE));
        setBombsCount(difficult.getBombsCount());
        addBombCounterImage();
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createButtonsPanel(int rows, int cols) {
        cellButtons = new JButton[rows][cols];
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(25 * cols, 25 * rows));
        buttonsPanel.setLayout(new GridLayout(rows, cols, 0, 0));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                final int x = j;
                final int y = i;

                cellButtons[y][x] = new JButton(GameImage.CLOSED.getImageIcon());
                cellButtons[y][x].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (listener == null) {
                            return;
                        }

                        switch (e.getButton()) {
                            case MouseEvent.BUTTON1 -> listener.onMouseClick(x, y, ButtonType.LEFT_BUTTON);
                            case MouseEvent.BUTTON2 -> listener.onMouseClick(x, y, ButtonType.MIDDLE_BUTTON);
                            case MouseEvent.BUTTON3 -> listener.onMouseClick(x, y, ButtonType.RIGHT_BUTTON);
                        }
                    }
                });
                buttonsPanel.add(cellButtons[y][x]);
            }
        }

        return buttonsPanel;
    }

    private void addButtonsPanel(JPanel buttonsPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.insets = new Insets(20, 20, 5, 20);
        mainLayout.setConstraints(buttonsPanel, gbc);
        contentPane.add(buttonsPanel);
    }

    private void addTimerImage() {
        JLabel label = new JLabel(GameImage.TIMER.getImageIcon());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 20, 0, 0);
        gbc.weightx = 0.1;
        mainLayout.setConstraints(label, gbc);
        contentPane.add(label);
    }

    private void addTimerLabel(JLabel timerLabel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 5, 0, 0);
        mainLayout.setConstraints(timerLabel, gbc);
        contentPane.add(timerLabel);
    }

    private void addBombCounter(JLabel bombsCounterLabel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 0.7;
        mainLayout.setConstraints(bombsCounterLabel, gbc);
        contentPane.add(bombsCounterLabel);
    }

    private void addBombCounterImage() {
        JLabel label = new JLabel(GameImage.BOMB_ICON.getImageIcon());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 3;
        gbc.insets = new Insets(0, 5, 0, 25);
        gbc.weightx = 0.1;
        mainLayout.setConstraints(label, gbc);
        contentPane.add(label);
    }

    @Override
    public void update(Model model) {
        if (model.getGameStatus() == GameStatus.RESTARTED) {
            createGameField(difficult.getRows(), difficult.getCols());
            return;
        }

        setBombsCount(model.getDifficult().getBombsCount() - model.getFlagsCount());
        Cell[][] field = model.getField();

        for (int i = 0; i < model.getDifficult().getCols(); i++) {
            for (int j = 0; j < model.getDifficult().getRows(); j++) {
                if (field[i][j].isContainsFlag()) {
                    setCellImage(i, j, GameImage.MARKED);
                } else {
                    setCellImage(i, j, GameImage.CLOSED);
                }

                if (field[i][j].isOpen()) {
                    if (field[i][j].isContainsActivatedBomb()) {
                        setCellImage(i, j, GameImage.ACTIVE_MINE);
                    } else if (field[i][j].isContainsBomb()) {
                        setCellImage(i, j, GameImage.BOMB);
                    } else if (field[i][j].getBombsAround() > 0) {
                        setCellImage(i, j, GameImage.values()[field[i][j].getBombsAround() - 1]);
                    } else {
                        setCellImage(i, j, GameImage.EMPTY);
                    }
                }
            }
        }
        checkGameStatus(model);
    }

    private void checkGameStatus(Model model) {
        if (model.getGameStatus() == GameStatus.IN_PROGRESS) {
            return;
        }

        if (model.getGameStatus() == GameStatus.LOSE) {
            new LoseWindow(this, e -> restart(model.getDifficult()), e -> this.dispose());
        } else if (model.getGameStatus() == GameStatus.WIN) {
            String[] data = controller.getBestRecord(model.getDifficult());

            if (data.length != 0) {
                Time record = Time.valueOf(data[1]);

                if (record.after(Utils.convertMillisToTime(controller.getCurrentMillis()))) {
                    createRecordsWindow(model);
                } else {
                    new WinWindow(this, e -> restart(model.getDifficult()), e -> this.dispose());
                }
            } else {
                createRecordsWindow(model);
            }
        }
    }

    private void createRecordsWindow(Model model) {
        new RecordsWindow(this,
                name -> {
                    restart(model.getDifficult());
                    controller.insertRecordIntoDatabase(name, Utils.convertMillisToTime(controller.getCurrentMillis()),
                            model.getDifficult());
                });
    }

    @Override
    public void updateTime(GameTimer timer) {
        long millis = timer.getMillis();
        setTimerValue(Utils.convertMillisToTime(millis));
    }

    private void setTimerValue(Time time) {
        timerLabel.setText(time.toString());
    }

    private void setNewGameMenuAction(ActionListener listener) {
        newGameMenu.addActionListener(listener);
    }

    private void setHighScoresMenuAction(ActionListener listener) {
        highScoresMenu.addActionListener(listener);
    }

    private void setSettingsMenuAction(ActionListener listener) {
        settingsMenu.addActionListener(listener);
    }

    private void setExitMenuAction(ActionListener listener) {
        exitMenu.addActionListener(listener);
    }

    private void setCellListener(CellEventListener listener) {
        this.listener = listener;
    }

    private void setCellImage(int x, int y, GameImage gameImage) {
        cellButtons[y][x].setIcon(gameImage.getImageIcon());
    }

    private void setBombsCount(int bombsCount) {
        bombsCounterLabel.setText(String.valueOf(bombsCount));
    }
}
