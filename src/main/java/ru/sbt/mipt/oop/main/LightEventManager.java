package ru.sbt.mipt.oop.main;

import org.springframework.stereotype.Component;

import static ru.sbt.mipt.oop.main.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.main.SensorEventType.LIGHT_ON;

public class LightEventManager implements EventHandler {

    public void manage(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            smartHome.execute(obj -> {
                if(obj instanceof Light){
                    Light light = (Light) obj;
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            light.setOn(true);
                            System.out.println("Light " + light.getId() + " was turned on.");
                        } else {
                            light.setOn(false);
                            System.out.println("Light " + light.getId() + " was turned off.");
                        }
                    }
                }
            });
        }
    }
}
