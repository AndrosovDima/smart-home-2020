package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.MakeAllLightOff.makeAllLightOff;

public class IfRoomIsHallTurnOffLight {
    static void ifRoomIsHallTurnOffLight(SmartHome smartHome, Room room) {
        if (room.getName().equals("hall")) {
            makeAllLightOff(smartHome);
        }
    }
}
