package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private final SmartHomeGettable smartHomeGettable;

    public Application(SmartHomeGettable smartHomeGettable) {
        this.smartHomeGettable = smartHomeGettable;
    }

    public static void main(String... args) throws IOException {
        List<EventHandler> allEventHandler = new ArrayList<>();
        allEventHandler.add(new LightEventManager());
        allEventHandler.add(new DoorEventManager());
        allEventHandler.add(new HallDoorEventManager());
        Application application = new Application(new SmartHomeGetter());
        // считываем состояние дома из файла
        SmartHome smartHome = application.smartHomeGettable.loadHome();
        // начинаем цикл обработки событий
        new AllEventsManager(allEventHandler, new EventGetter()).manageAllEvents(smartHome);
    }
}