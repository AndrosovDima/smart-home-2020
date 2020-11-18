package ru.sbt.mipt.oop.main;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.APIEventHandler;

import java.util.Map;

public class EventHandlerAdapter implements APIEventHandler {

    private final SmartHome smartHome;
    private final EventHandler eventHandler;
    private final Map<String, SensorEventType> mapForConvertingCCSensorEventsToSensorEvents;

    public EventHandlerAdapter(SmartHome smartHome, EventHandler eventHandler, Map<String, SensorEventType> mapForConvertingCCSensorEventsToSensorEvents) {
        this.smartHome = smartHome;
        this.eventHandler = eventHandler;
        this.mapForConvertingCCSensorEventsToSensorEvents = mapForConvertingCCSensorEventsToSensorEvents;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = new SensorEventAdapter(event, mapForConvertingCCSensorEventsToSensorEvents).getSensorEvent();
        eventHandler.manage(smartHome, sensorEvent);
    }
}
