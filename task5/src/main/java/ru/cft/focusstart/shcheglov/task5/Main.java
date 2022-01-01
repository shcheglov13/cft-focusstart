package ru.cft.focusstart.shcheglov.task5;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.shcheglov.task5.utils.Utils;
import ru.cft.focusstart.shcheglov.task5.entities.Consumer;
import ru.cft.focusstart.shcheglov.task5.entities.Producer;
import ru.cft.focusstart.shcheglov.task5.utils.WorkersType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Программа запущена");
        try {
            for (Thread producer : createWorkers(WorkersType.PRODUCER)) {
                producer.start();
            }

            for (Thread consumer : createWorkers(WorkersType.CONSUMER)) {
                consumer.start();
            }
        } catch (IOException e) {
            log.error("Возникла ошибка ввода/вывода. Причина: {}", e.getLocalizedMessage());
        }
    }

    private static List<Thread> createWorkers(WorkersType workerType) throws IOException {
        List<Thread> threads = new ArrayList<>();

        switch (workerType) {
            case PRODUCER -> {
                for (int i = 0; i < Utils.getProducersCount(); i++) {
                    threads.add(new Thread(new Producer(i, Utils.getProducersTime(), Utils.getStorage())));
                    threads.get(i).setName(String.format("Producer-%d", i));
                }
            }
            case CONSUMER -> {
                for (int i = 0; i < Utils.getConsumerCount(); i++) {
                    threads.add(new Thread(new Consumer(i, Utils.getConsumerTime(), Utils.getStorage())));
                    threads.get(i).setName(String.format("Consumer-%d", i));
                }
            }
        }

        return threads;
    }
}
