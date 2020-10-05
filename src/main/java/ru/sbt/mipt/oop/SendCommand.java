package ru.sbt.mipt.oop;

public class SendCommand implements CommandSendable{
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
