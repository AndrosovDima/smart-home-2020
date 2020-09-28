package ru.sbt.mipt.oop;

public class LightOnOffEvent {
    static void lightEvent(Room room, Light light, boolean b, String s) {
        light.setOn(b);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + s);
    }
}
