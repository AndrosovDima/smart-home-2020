package ru.sbt.mipt.oop.main;

import static ru.sbt.mipt.oop.main.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.main.SensorEventType.ALARM_DEACTIVATE;

public class SensorEvent {
    private final SensorEventType type;
    private final String objectId;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (this.type == ALARM_ACTIVATE || this.type == ALARM_DEACTIVATE){
            this.code = code;
        }
    }

    public SensorEvent(SensorEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    public SensorEventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
