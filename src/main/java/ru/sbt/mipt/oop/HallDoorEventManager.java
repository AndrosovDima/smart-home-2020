package ru.sbt.mipt.oop;

public class HallDoorEventManager implements EventHandler {
    @Override
    public void manage(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId()) && room.getName().equals("hall")) {
                        new AllLightOffMaker().allLightOffMaker(smartHome);
                    }
                }
            }
        }
    }
}