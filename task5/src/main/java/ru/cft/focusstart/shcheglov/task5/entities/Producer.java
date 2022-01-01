package ru.cft.focusstart.shcheglov.task5.entities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Producer implements Runnable {
    private final long producerTime;
    private final Storage storage;
    private final long id;

    public Producer(long id, long producerTime, Storage storage) {
        this.id = id;
        this.producerTime = producerTime;
        this.storage = storage;
        log.info("Создан производитель с id - {}", id);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Resource resource = new Resource();
                log.info("Производитель c id-{} произвел ресурс с id-{}", id, resource.getId());
                storage.putResource(resource);
                Thread.sleep(producerTime);
            } catch (InterruptedException e) {
                log.info("Поток {} прерван. Причина: {}", Thread.currentThread().getName(), e.getLocalizedMessage());
            }
        }
    }
}
