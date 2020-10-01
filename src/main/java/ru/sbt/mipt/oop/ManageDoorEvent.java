package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.DoorOpenCloseEvent.doorEvent;
import static ru.sbt.mipt.oop.IfRoomIsHallTurnOffLight.ifRoomIsHallTurnOffLight;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class ManageDoorEvent {

    static void manageDoorEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            makeDoorEventForRoom(smartHome, event, room);
        }
    }

    private static void makeDoorEventForRoom(SmartHome smartHome, SensorEvent event, Room room) {
        for (Door door : room.getDoors()) {
            if (door.getId().equals(event.getObjectId())) {
                if (event.getType() == DOOR_OPEN) {
                    doorEvent(room, door, true, " was opened.");
                } else {
                    doorEvent(room, door, false, " was closed.");
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                    ifRoomIsHallTurnOffLight(smartHome, room);
                }
            }
        }
    }
}
