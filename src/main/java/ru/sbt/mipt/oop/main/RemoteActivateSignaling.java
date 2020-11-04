package ru.sbt.mipt.oop.main;

import org.springframework.stereotype.Component;

@Component
public class RemoteActivateSignaling implements RemoteCommand {
    private final SmartHome smartHome;

    public RemoteActivateSignaling(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(obj -> {
            if (obj instanceof Signaling){
                Signaling signaling = (Signaling) obj;
                signaling.activateSignaling("0000");
            }
        });
    }
}
