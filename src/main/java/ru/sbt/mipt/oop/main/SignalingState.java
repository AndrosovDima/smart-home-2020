package ru.sbt.mipt.oop.main;

public interface SignalingState {
    void activateSignaling(String activatingCode);
    void deactivateSignaling(String activatingCode);
    void turnOnAlarmState();
}
