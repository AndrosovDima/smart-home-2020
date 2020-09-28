package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.GetNextSensorEvent.getNextSensorEvent;
import static ru.sbt.mipt.oop.ManageEvent.manageEvent;

public class ManageAllEvents {
    static void manageAllEvents(SmartHome smartHome, SensorEvent event) {
        while (event != null) {
            manageEvent(smartHome, event);
            event = getNextSensorEvent();
        }
    }
}
