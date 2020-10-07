package ru.sbt.mipt.oop.main;

public class TestCommandSender implements CommandSender {
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
