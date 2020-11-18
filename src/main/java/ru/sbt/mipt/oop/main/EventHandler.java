package ru.sbt.mipt.oop.main;

public interface EventHandler {
    void manage(SmartHome smartHome, SensorEvent event);
}
