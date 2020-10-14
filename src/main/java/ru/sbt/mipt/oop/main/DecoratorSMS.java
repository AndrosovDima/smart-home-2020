package ru.sbt.mipt.oop.main;

import static ru.sbt.mipt.oop.main.SensorEventType.*;

public class DecoratorSMS implements EventHandler {
    private final EventHandler eventHandler;

    public DecoratorSMS(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void manage(SmartHome smartHome, SensorEvent event) {
        Signaling signaling = smartHome.signaling;
        if (signaling.getState() instanceof AlarmState) return;
        if((event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED ||
            event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) &&
                ((!(signaling.getState() instanceof ActiveState)) || (!(signaling.getState() instanceof AlarmState)))) {
            eventHandler.manage(smartHome, event);
        } else{
            System.out.println("Sending sms");
        }
    }
}
