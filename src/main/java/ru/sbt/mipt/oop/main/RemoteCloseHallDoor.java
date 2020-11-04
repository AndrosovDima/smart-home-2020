package ru.sbt.mipt.oop.main;

import org.springframework.stereotype.Component;

@Component
public class RemoteCloseHallDoor implements RemoteCommand {
    private final SmartHome smartHome;

    public RemoteCloseHallDoor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(obj -> {
            if (obj instanceof Room){
                Room room = (Room) obj;
                if (room.getName().equals("hall")){
                    room.execute(a -> {
                        if (a instanceof Door){
                            Door door = (Door) a;
                            door.setOpen(false);
                            System.out.println("Door in hall was closed.");
                        }
                    });
                }
            }
        });
    }
}
