package ru.sbt.mipt.oop.main;

import static ru.sbt.mipt.oop.main.SensorEventType.*;

public class DecoratorAlarm implements EventHandler {
    private final EventHandler eventHandler;

    public DecoratorAlarm(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }
    @Override
    public void manage(SmartHome smartHome, SensorEvent event) {
        Signaling signaling = smartHome.signaling;
        eventHandler.manage(smartHome, event);
        if ((signaling.getState() instanceof ActiveState) &&
                (!(event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE))){
            signaling.turnOnAlarmState();
        }
    }
}
