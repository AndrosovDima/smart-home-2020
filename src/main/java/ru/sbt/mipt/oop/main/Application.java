package ru.sbt.mipt.oop.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private final SmartHomeGettable smartHomeGettable;

    public Application(SmartHomeGettable smartHomeGettable) {
        this.smartHomeGettable = smartHomeGettable;
    }

    public static void main(String... args) throws IOException {
        List<DoorsAndLightsManager> allDoorsAndLightsManager = new ArrayList<>();
        allDoorsAndLightsManager.add(new LightEventManager());
        allDoorsAndLightsManager.add(new DoorEventManager());
        allDoorsAndLightsManager.add(new HallDoorEventManager());
        Application application = new Application(new SmartHomeGetter());
        // считываем состояние дома из файла
        SmartHome smartHome = application.smartHomeGettable.loadHome();
        // начинаем цикл обработки событий
        new AllEventsManager(allDoorsAndLightsManager, new NextSensorEventGetter()).manageAllEvents(smartHome);
    }
}