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
        if((signaling.getState() instanceof NonActiveState)) {
            eventHandler.manage(smartHome, event);
        }
        if (!(event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE)){
            System.out.println("Sending sms");
        }
    }
}
