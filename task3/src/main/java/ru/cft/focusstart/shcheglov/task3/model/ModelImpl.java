package ru.cft.focusstart.shcheglov.task3.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.shcheglov.task3.enums.GameStatus;
import ru.cft.focusstart.shcheglov.task3.enums.GameDifficult;
import ru.cft.focusstart.shcheglov.task3.view.Listeners.ModelListener;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Getter
public class ModelImpl implements Model {
    @Getter(AccessLevel.NONE)
    private final List<ModelListener> listeners;
    private GameDifficult difficult;
    private Cell[][] field;
    private GameStatus gameStatus;
    private int flagsCount;

    public ModelImpl(GameDifficult difficult) {
        field = initField(difficult);
        gameStatus = GameStatus.FIRST_MOVE;
        listeners = new ArrayList<>();
        this.difficult = difficult;

        log.info("Модель инициализирована. Сложность: {}", difficult.getName());
    }

    private Cell[][] initField(GameDifficult difficult) {
        Cell[][] field = new Cell[difficult.getCols()][difficult.getRows()];

        for (int i = 0; i < difficult.getCols(); i++) {
            for (int j = 0; j < difficult.getRows(); j++) {
                field[i][j] = new Cell(i, j);
            }
        }

        return field;
    }

    @Override
    public void openCellsAround(int x, int y) {
        if (gameStatus == GameStatus.LOSE || gameStatus == GameStatus.WIN) {
            return;
        }

        if (field[x][y].isOpen() && field[x][y].getBombsAround() == calculateFlagsAroundCell(x, y)
                && field[x][y].getBombsAround() > 0 && !field[x][y].isContainsBomb()) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (inField(i, j)) {
                        if (!field[i][j].isContainsFlag()) {
                            field[i][j].setOpen(true);
                            openEmptyCells(x, y);

                            if (field[i][j].isContainsBomb()) {
                                gameStatus = GameStatus.LOSE;
                                field[i][j].setContainsActivatedBomb(true);
                            }
                        }
                    }
                }
            }

            if (calculateClosedCells() == difficult.getBombsCount()) {
                gameStatus = GameStatus.WIN;
                log.info("Победа!");
            }

            if (gameStatus == GameStatus.LOSE) {
                openAllCells();
                log.info("Поражение:( Взорвались мины вокруг ячейки x:{}, y:{}", x, y);
            }

            notifyListeners();
        }
    }

    private int calculateFlagsAroundCell(int x, int y) {
        int flagsAroundCell = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (inField(i, j)) {
                    if (x == i && y == j) {
                        continue;
                    }

                    if (field[i][j].isContainsFlag()) {
                        flagsAroundCell++;
                    }
                }
            }
        }

        return flagsAroundCell;
    }

    @Override
    public void setFlag(int x, int y) {
        if (gameStatus == GameStatus.LOSE || gameStatus == GameStatus.WIN) {
            return;
        }

        Cell cell = field[x][y];
        if (!cell.isOpen()) {
            if (flagsCount < difficult.getBombsCount()) {
                cell.setContainsFlag(!cell.isContainsFlag());
                log.info(cell.isContainsFlag() ?
                        "Установлен флаг в ячейку x:{}, y:{}" : "Убран флаг из ячейки x:{}, y:{}", x, y);
            } else {
                cell.setContainsFlag(false);
            }

            flagsCount = (int) Arrays.stream(field)
                    .flatMap(Arrays::stream)
                    .filter(Cell::isContainsFlag)
                    .count();

            notifyListeners();
        }
    }

    @Override
    public void openCell(int x, int y) {
        if (gameStatus == GameStatus.LOSE || gameStatus == GameStatus.WIN) {
            return;
        }

        if (gameStatus == GameStatus.FIRST_MOVE) {
            placeBombs(field[x][y]);
            gameStatus = GameStatus.IN_PROGRESS;
        }

        Cell cell = field[x][y];

        if (!cell.isContainsFlag()) {
            cell.setOpen(true);
            log.info("Открыта ячейка x:{}, y:{}", x, y);

            if (cell.isContainsBomb()) {
                gameStatus = GameStatus.LOSE;
                cell.setContainsActivatedBomb(true);
                openAllCells();

                log.info("Поражение:( Взорвалась мина в ячейке x:{}, y:{}", x, y);
            } else if (cell.getBombsAround() == 0) {
                openEmptyCells(x, y);
            }

            if (calculateClosedCells() == difficult.getBombsCount()) {
                gameStatus = GameStatus.WIN;
                log.info("Победа!");
            }
        }

        notifyListeners();
    }

    private int calculateClosedCells() {
        return (int) Arrays.stream(field)
                .flatMap(Arrays::stream)
                .filter(c -> !c.isOpen())
                .count();
    }

    private void placeBombs(Cell immunityCell) {
        List<Cell> cellsList = Arrays.stream(field)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        Collections.shuffle(cellsList);

        cellsList.stream()
                .filter(cell -> cell != immunityCell)
                .limit(difficult.getBombsCount())
                .forEach(cell -> {
                    cell.setContainsBomb(true);
                    incrementBombCounterInCellsAround(cell.getX(), cell.getY());
                    log.info("Заминирована ячейка x:{}, y:{}", cell.getX(), cell.getY());
                });

        log.info("Все мины размещены на игровом поле. Количество мин: {}", difficult.getBombsCount());
    }

    private void incrementBombCounterInCellsAround(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (inField(i, j)) {
                    if (x == i && y == j) {
                        continue;
                    }
                    field[i][j].incrementBombsAround();
                }
            }
        }
    }

    private void openEmptyCells(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (inField(i, j)) {
                    if (x == i && y == j) {
                        continue;
                    }

                    if (field[i][j].isOpen() || field[i][j].isContainsBomb() || field[i][j].isContainsFlag()) {
                        continue;
                    }

                    if (field[i][j].getBombsAround() > 0) {
                        field[i][j].setOpen(true);
                        log.info("Открыта ячейка x:{}, y:{}", i, j);
                        continue;
                    }

                    field[i][j].setOpen(true);
                    log.info("Открыта ячейка x:{}, y:{}", i, j);
                    openEmptyCells(i, j);
                }
            }
        }
    }

    private void openAllCells() {
        Arrays.stream(field)
                .flatMap(Arrays::stream)
                .forEach(cell -> cell.setOpen(true));
    }

    private boolean inField(int x, int y) {
        return x >= 0 && x < difficult.getCols() && y >= 0 && y < difficult.getRows();
    }

    private void notifyListeners() {
        for (ModelListener listener : listeners) {
            listener.update(this);
        }
    }

    @Override
    public void subscribeListener(ModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void refresh(GameDifficult difficult) {
        if (!difficult.equals(this.difficult)) {
            field = initField(difficult);
        } else {
            Arrays.stream(field)
                    .flatMap(Arrays::stream)
                    .forEach(Cell::reset);
        }

        this.difficult = difficult;
        flagsCount = 0;
        gameStatus = GameStatus.RESTARTED;
        notifyListeners();
        gameStatus = GameStatus.FIRST_MOVE;
    }
}
