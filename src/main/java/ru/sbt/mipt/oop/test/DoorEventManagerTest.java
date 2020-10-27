package ru.sbt.mipt.oop.test;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.main.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventManagerTest {

    @Test
    void testsManageForDoor_returnsTrue_whenDoorCloses() {
        // given
        List<EventHandler> allEventHandler = new ArrayList<>();
        allEventHandler.add(new LightEventManager());
        allEventHandler.add(new DoorEventManager());
        SmartHome smartHome = new SmartHome();
        Door door = new Door(true, "1");
        ArrayList<Door> doors = new ArrayList<>();
        doors.add(door);
        Room room = new Room(new ArrayList<Light>(), doors, "kitchen");
        smartHome.addRoom(room);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        for (EventHandler elem : allEventHandler){
            elem.manage(smartHome, event);
        }
        // when
        boolean isSuccess = !(door.isOpen());
        // then
        assertTrue (isSuccess);
    }

    @Test
    void testsManageForDoor_returnsTrue_whenDoorOpens() {
        // given
        List<EventHandler> allEventHandler = new ArrayList<>();
        allEventHandler.add(new LightEventManager());
        allEventHandler.add(new DoorEventManager());
        allEventHandler.add(new HallDoorEventManager());
        SmartHome smartHome = new SmartHome();
        Door door = new Door(false, "1");
        ArrayList<Door> doors = new ArrayList<>();
        doors.add(door);
        Room room = new Room(new ArrayList<Light>(), doors, "kitchen");
        smartHome.addRoom(room);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        for (EventHandler elem : allEventHandler){
            elem.manage(smartHome, event);
        }
        // when
        boolean isSuccess = door.isOpen();
        // then
        assertTrue (isSuccess);
    }
}