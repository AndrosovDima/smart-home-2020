package ru.sbt.mipt.oop.main;

import org.springframework.stereotype.Component;

@Component
public class RemoteAlarmActivation implements RemoteCommand {
    private final SmartHome smartHome;

    public RemoteAlarmActivation(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(obj -> {
            if (obj instanceof Signaling){
                Signaling signaling = (Signaling) obj;
                signaling.turnOnAlarmState();
            }
        });
    }
}
