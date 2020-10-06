package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class ManageDoorEvent implements DoorsAndLightsManager {

    public void manage(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                        } else {
                            door.setOpen(false);
                            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");

                            if (room.getName().equals("hall")) {
                                for (Room homeRoom : smartHome.getRooms()) {
                                    for (Light light : homeRoom.getLights()) {
                                        light.setOn(false);
                                        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                        TestCommandSender commandSendable = new TestCommandSender();
                                        commandSendable.sendCommand(command);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
