package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.shcheglov.task3.enums.GameDifficult;
import ru.cft.focusstart.shcheglov.task3.enums.GameStatus;
import ru.cft.focusstart.shcheglov.task3.model.Cell;
import ru.cft.focusstart.shcheglov.task3.model.Model;
import ru.cft.focusstart.shcheglov.task3.model.ModelImpl;

import java.util.Arrays;
import java.util.Optional;

public class ModelTest {
    private Model model;
    private final int x = 4;
    private final int y = 4;

    @BeforeEach
    public void init() {
        model = new ModelImpl(GameDifficult.NOVICE);
    }

    @Test
    public void openCellTest() {
        model.openCell(x, y);
        Assertions.assertTrue(model.getField()[x][y].isOpen());
    }

    @Test
    public void bombsNotPlacedBeforeFirstMoveTest() {
        Optional<Cell> cell = Arrays.stream(model.getField())
                .flatMap(Arrays::stream)
                .filter(Cell::isContainsBomb)
                .findFirst();
        Assertions.assertFalse(cell.isPresent());
    }

    @Test
    public void setFlagTest() {
        model.setFlag(x, y);
        Assertions.assertTrue(model.getField()[x][y].isContainsFlag());
    }

    @Test
    public void unsetFlagTest() {
        model.setFlag(x, y);
        model.setFlag(x, y);
        Assertions.assertFalse(model.getField()[x][y].isContainsFlag());
    }

    @Test
    public void cellWithFlagWillNotOpenTest() {
        model.setFlag(x, y);
        model.openCell(x, y);
        Assertions.assertFalse(model.getField()[x][y].isOpen());
    }

    @Test
    public void gameStatusIsLoseIfOpenedCellWithMine() {
        model.getField()[x][y].setContainsBomb(true);
        model.openCell(x, y);
        Assertions.assertSame(model.getGameStatus(), GameStatus.LOSE);
    }

    @Test
    public void gameStatusIsWinIfOpenedAllCellsWithoutMines() {
        model.openCell(x, y);
        Arrays.stream(model.getField())
                .flatMap(Arrays::stream)
                .filter(cell -> !cell.isContainsBomb()).forEach(cell -> cell.setOpen(true));
        model.openCell(x, y);
        Assertions.assertSame(model.getGameStatus(), GameStatus.WIN);
    }

    @Test
    public void closedCellsWillNotOpenAroundNumberIfFlagsNotEqualsThatNumber() {
        model.getField()[x][y].setBombsAround(3);
        model.getField()[x][y].setOpen(true);

        model.getField()[x - 1][y].setContainsFlag(true);
        model.getField()[x + 1][y].setContainsFlag(true);

        model.openCellsAround(x, y);

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                Assertions.assertFalse(model.getField()[i][j].isOpen());
            }
        }
    }

    @Test
    public void closedCellsWillOpenAroundNumberIfFlagsEqualsThatNumber() {
        model.getField()[x][y].setBombsAround(3);
        model.getField()[x][y].setOpen(true);

        model.getField()[x - 1][y].setContainsFlag(true);
        model.getField()[x + 1][y].setContainsFlag(true);
        model.getField()[x + 1][y + 1].setContainsFlag(true);

        model.openCellsAround(x, y);

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y || model.getField()[i][j].isContainsFlag()) {
                    continue;
                }

                Assertions.assertTrue(model.getField()[i][j].isOpen());
            }
        }
    }

    @Test
    public void firstOpenedCellNotContainMine() {
        model.openCell(x, y);
        Assertions.assertFalse(model.getField()[x][y].isContainsBomb());
    }
}
