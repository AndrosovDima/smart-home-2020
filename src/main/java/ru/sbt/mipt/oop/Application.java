package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.GetNextSensorEvent.getNextSensorEvent;
import static ru.sbt.mipt.oop.ManageAllEvents.manageAllEvents;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = GetSmartHome.getSmartHome();
        // начинаем цикл обработки событий
        SensorEvent event = getNextSensorEvent();
        manageAllEvents(smartHome, event);
    }

    static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}