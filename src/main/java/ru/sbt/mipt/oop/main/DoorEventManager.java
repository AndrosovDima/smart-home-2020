package ru.sbt.mipt.oop.main;

import static ru.sbt.mipt.oop.main.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.main.SensorEventType.DOOR_OPEN;

public class DoorEventManager implements DoorsAndLightsManager {

    public void manage(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            smartHome.execute(obj -> {
                if (obj instanceof Door){
                    Door door = (Door) obj;
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                            System.out.println("Door " + door.getId() + " was opened.");
                        } else {
                            door.setOpen(false);
                            System.out.println("Door " + door.getId() + " was closed.");
                        }
                    }
                }
            });
        }
    }
}
