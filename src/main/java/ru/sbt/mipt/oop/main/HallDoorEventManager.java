package ru.sbt.mipt.oop.main;

import org.springframework.stereotype.Component;

import static ru.sbt.mipt.oop.main.SensorEventType.DOOR_CLOSED;

@Component
public class HallDoorEventManager implements EventHandler {
    @Override
    public void manage(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_CLOSED){
            smartHome.execute(obj -> {
                if (obj instanceof Room){
                    Room room = (Room) obj;
                    if (room.getName().equals("hall")){
                        room.execute(object -> {
                            if (object instanceof Door){
                                Door door = (Door) object;
                                if (door.getId().equals(event.getObjectId())){
                                    this.closeDoorAndOffAllLight(smartHome, door);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    private void closeDoorAndOffAllLight(SmartHome smartHome, Door door) {
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " was closed.");
        smartHome.execute(obj -> {
            if (obj instanceof Light){
                Light light = (Light) obj;
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                TestCommandSender commandSendable = new TestCommandSender();
                commandSendable.sendCommand(command);
            }
        });
    }
}
