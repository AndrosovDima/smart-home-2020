package ru.sbt.mipt.oop;

public class AllLightOffMaker {
    public void allLightOffMaker(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                TestCommandSender commandSendable = new TestCommandSender();
                commandSendable.sendCommand(command);
            }
        }
    }
}
