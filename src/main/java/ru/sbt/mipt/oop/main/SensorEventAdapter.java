package ru.sbt.mipt.oop.main;

import com.coolcompany.smarthome.events.CCSensorEvent;

import java.util.HashMap;
import java.util.Map;

public class SensorEventAdapter {

    private final SensorEvent sensorEvent;

    public SensorEvent getSensorEvent() {
        return sensorEvent;
    }

    public SensorEventAdapter(CCSensorEvent ccSensorEvent) {
        HashMap<String, SensorEventType> mapForConvertingCCSensorEventsToSensorEvents = new HashMap<>(
                Map.of("LightIsOn", SensorEventType.LIGHT_ON,
                        "LightIsOff", SensorEventType.LIGHT_OFF,
                        "DoorIsOpen", SensorEventType.DOOR_OPEN,
                        "DoorIsClosed", SensorEventType.DOOR_CLOSED,
                        "DoorIsLocked", SensorEventType.ALARM_ACTIVATE,
                        "DoorIsUnlocked", SensorEventType.ALARM_DEACTIVATE)
        );
        this.sensorEvent = new SensorEvent(mapForConvertingCCSensorEventsToSensorEvents.get(ccSensorEvent.getEventType()),
                ccSensorEvent.getObjectId());
    }
}
