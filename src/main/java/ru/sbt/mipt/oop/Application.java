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
        List<DoorsAndLightsManager> allDoorsAndLightsManager = new ArrayList<>();
        allDoorsAndLightsManager.add(new ManageLightEvent());
        allDoorsAndLightsManager.add(new ManageDoorEvent());
        Application application = new Application(new GetSmartHome());
        // считываем состояние дома из файла
        SmartHome smartHome = application.smartHomeGettable.loadHome();
        // начинаем цикл обработки событий
        new ManageAllEvents(allDoorsAndLightsManager, new GetNextSensorEvent()).manageAllEvents(smartHome);
    }
}