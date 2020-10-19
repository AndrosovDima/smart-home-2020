package ru.sbt.mipt.oop.main;

import static ru.sbt.mipt.oop.main.SensorEventType.*;

public class AlarmManager implements EventHandler {
    @Override
    public void manage(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE) {
            smartHome.execute(obj -> {
                if(obj instanceof Signaling){
                    Signaling signaling = (Signaling) obj;
                    if (event.getType() == ALARM_ACTIVATE) {
                        signaling.activateSignaling(event.getCode());
                    } else {
                        signaling.deactivateSignaling(event.getCode());
                    }
                }
            });
        }
    }
}