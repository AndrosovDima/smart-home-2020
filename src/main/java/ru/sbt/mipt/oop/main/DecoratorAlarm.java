package ru.sbt.mipt.oop.main;

import static ru.sbt.mipt.oop.main.SensorEventType.*;
import static ru.sbt.mipt.oop.main.SensorEventType.LIGHT_OFF;

public class DecoratorAlarm implements EventHandler {
    private final EventHandler eventHandler;

    public DecoratorAlarm(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }
    @Override
    public void manage(SmartHome smartHome, SensorEvent event) {
        Signaling signaling = smartHome.signaling;
        eventHandler.manage(smartHome, event);
        if ((signaling.getState() instanceof ActiveState) && (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED ||
                event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF)){
                signaling.turnOnAlarmState();
        }
    }
}
