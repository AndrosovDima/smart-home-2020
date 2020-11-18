package ru.sbt.mipt.oop.main;

import org.springframework.stereotype.Component;

@Component
public class RemoteTurnOffAllLight implements RemoteCommand {
    private final SmartHome smartHome;

    public RemoteTurnOffAllLight(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(obj -> {
            if (obj instanceof Light){
                Light light = (Light) obj;
                light.setOn(false);
                System.out.println("Light " + light.getId() + " was turned off.");
            }
        });
    }
}
