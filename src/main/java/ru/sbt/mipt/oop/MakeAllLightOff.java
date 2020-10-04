package ru.sbt.mipt.oop;

public class MakeAllLightOff {
    static void makeAllLightOff(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                Application.sendCommand(command);
            }
        }
    }
}
