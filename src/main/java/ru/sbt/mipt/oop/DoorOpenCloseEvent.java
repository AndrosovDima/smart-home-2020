package ru.sbt.mipt.oop;

public class DoorOpenCloseEvent {
    static void doorEvent(Room room, Door door, boolean b, String s) {
        door.setOpen(b);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + s);
    }
}
