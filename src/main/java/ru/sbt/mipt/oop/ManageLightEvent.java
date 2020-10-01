package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.LightOnOffEvent.lightEvent;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class ManageLightEvent {

    static void manageLightEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            makeLightEventForRoom(event, room);
        }
    }

    private static void makeLightEventForRoom(SensorEvent event, Room room) {
        for (Light light : room.getLights()) {
            if (light.getId().equals(event.getObjectId())) {
                if (event.getType() == LIGHT_ON) {
                    lightEvent(room, light, true, " was turned on.");
                } else {
                    lightEvent(room, light, false, " was turned off.");
                }
            }
        }
    }
}
