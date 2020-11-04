package ru.sbt.mipt.oop.main;

import org.springframework.stereotype.Component;

@Component
public class RemoteTurnOnHallLight implements RemoteCommand {
    private final SmartHome smartHome;

    public RemoteTurnOnHallLight(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(obj -> {
            if (obj instanceof Room){
                Room room = (Room) obj;
                if (room.getName().equals("hall")){
                    room.execute(a -> {
                        if (a instanceof Light){
                            Light light = (Light) a;
                            light.setOn(true);
                            System.out.println("Light in hall was turned on.");
                        }
                    });
                }
            }
        });
    }
}
