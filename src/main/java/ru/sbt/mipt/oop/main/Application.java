package ru.sbt.mipt.oop.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    private final SmartHomeGettable smartHomeGettable;

    public Application(SmartHomeGettable smartHomeGettable) {
        this.smartHomeGettable = smartHomeGettable;
    }

    public static void main(String... args) throws IOException {
        List<EventHandler> allEventHandler = new ArrayList<>(Arrays.asList(new AlarmManager(),
                                                                new DecoratorAlarm(new DecoratorSMS(new LightEventManager())),
                                                                new DecoratorAlarm(new DecoratorSMS(new DoorEventManager())),
                                                                new DecoratorAlarm(new DecoratorSMS(new HallDoorEventManager()))));
        Application application = new Application(new SmartHomeGetter());
        // считываем состояние дома из файла
        SmartHome smartHome = application.smartHomeGettable.loadHome();
        // начинаем цикл обработки событий
        new AllEventsManager(allEventHandler, new EventGetter()).manageAllEvents(smartHome);
    }
}