package ru.cft.focusstart.shcheglov.task5.entities;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Storage {
    private final int capacity;
    private final List<Resource> resources;

    public Storage(int capacity) {
        this.capacity = capacity;
        this.resources = new ArrayList<>(capacity);
        log.info("Хранилище с вместимостью {} создано", capacity);
    }

    public synchronized Resource takeResource() throws InterruptedException {
        while (resources.size() == 0) {
            wait();
            log.info("В хранилище кончились ресурсы. Потребитель {} ждет", Thread.currentThread().getName());
        }

        Resource resource = resources.get(0);
        resources.remove(0);
        log.info("Ресурс с id-{} убыл со склада. Количество ресурсов на складе: {}", resource.getId(), resources.size());
        notifyAll();

        return resource;
    }

    public synchronized void putResource(Resource resource) throws InterruptedException {
        while (resources.size() >= capacity) {
            log.info("Хранилище переполнено. Производитель {} ждет", Thread.currentThread().getName());
            wait();
        }

        resources.add(resource);
        log.info("Ресурс с id-{} добавлен на склад. Количество ресурсов на складе: {}", resource.getId(), resources.size());
        notifyAll();
    }
}
