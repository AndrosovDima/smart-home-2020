package ru.sbt.mipt.oop.main;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.APIEventHandler;

public class EventHandlerAdapter implements APIEventHandler {

    private final SmartHome smartHome;
    private final EventHandler eventHandler;

    public EventHandlerAdapter(SmartHome smartHome, EventHandler eventHandler) {
        this.eventHandler = eventHandler;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = new SensorEventAdapter(event).getSensorEvent();
        eventHandler.manage(smartHome, sensorEvent);
    }
}
