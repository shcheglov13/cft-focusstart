package ru.cft.focusstart.shcheglov.task3;

import ru.cft.focusstart.shcheglov.task3.controller.Controller;
import ru.cft.focusstart.shcheglov.task3.controller.ControllerImpl;
import ru.cft.focusstart.shcheglov.task3.model.GameTimer;
import ru.cft.focusstart.shcheglov.task3.model.GameTimerImpl;
import ru.cft.focusstart.shcheglov.task3.model.Model;
import ru.cft.focusstart.shcheglov.task3.model.ModelImpl;
import ru.cft.focusstart.shcheglov.task3.utils.Constants;
import ru.cft.focusstart.shcheglov.task3.view.MainWindow;

public class Game {
    public static void main(String[] args) {
        Model model = new ModelImpl(Constants.DEFAULT_DIFFICULT);
        GameTimer timer = new GameTimerImpl(model);
        Controller controller = new ControllerImpl(model, timer);
        MainWindow view = new MainWindow(controller);
    }
}
