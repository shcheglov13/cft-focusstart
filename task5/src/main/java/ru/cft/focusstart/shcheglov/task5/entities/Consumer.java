package ru.cft.focusstart.shcheglov.task5.entities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer implements Runnable {
    private final Storage storage;
    private final long consumeTime;
    private final long id;

    public Consumer(long id, long consumerTime, Storage storage) {
        this.id = id;
        this.storage = storage;
        this.consumeTime = consumerTime;
        log.info("Создан потребитель с id - {}", id);
    }

    @Override
    public void run() {
        while (true) {
            try {
                log.info("Потребитель c id-{} потребил ресурс c id-{}", id, storage.takeResource().getId());
                Thread.sleep(consumeTime);
            } catch (InterruptedException e) {
                log.info("Поток {} прерван. Причина: {}", Thread.currentThread().getName(), e.getLocalizedMessage());
            }
        }
    }
}
