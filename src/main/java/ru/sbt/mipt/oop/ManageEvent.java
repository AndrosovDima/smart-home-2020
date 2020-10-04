package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.ManageDoorEvent.manageDoorEvent;
import static ru.sbt.mipt.oop.ManageLightEvent.manageLightEvent;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class ManageEvent {
    static void manageEvent(SmartHome smartHome, SensorEvent event) {
        System.out.println("Got event: " + event);
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            // событие от источника света
            manageLightEvent(smartHome, event);
        }
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            manageDoorEvent(smartHome, event);
        }
    }
}
