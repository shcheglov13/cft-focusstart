package ru.cft.focusstart.shcheglov.task5.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.cft.focusstart.shcheglov.task5.utils.Utils;

@EqualsAndHashCode
@Getter
public class Resource {
    private final long id;

    public Resource() {
        this.id = Utils.getResourceId();
    }
}
