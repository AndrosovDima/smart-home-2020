package ru.sbt.mipt.oop.main;

import org.springframework.stereotype.Component;

@Component
public class RemoteTurnOnAllLight implements RemoteCommand {
    private final SmartHome smartHome;

    public RemoteTurnOnAllLight(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(obj -> {
            if (obj instanceof Light){
                Light light = (Light) obj;
                light.setOn(true);
                System.out.println("Light " + light.getId() + " was turned off.");
            }
        });
    }
}
