package ru.sbt.mipt.oop.main;

import com.coolcompany.smarthome.events.CCSensorEvent;

import java.util.Map;

public class SensorEventAdapter {

    private final SensorEvent sensorEvent;

    public SensorEvent getSensorEvent() {
        return sensorEvent;
    }

    public SensorEventAdapter(CCSensorEvent ccSensorEvent,
                              Map<String, SensorEventType> mapForConvertingCCSensorEventsToSensorEvents) {
        this.sensorEvent = new SensorEvent(mapForConvertingCCSensorEventsToSensorEvents
                .get(ccSensorEvent.getEventType()), ccSensorEvent.getObjectId());
    }
}
