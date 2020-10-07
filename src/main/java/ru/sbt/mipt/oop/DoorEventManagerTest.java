package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventManagerTest {

    @Test
    void testsManageForDoor_returnsTrue_whenDoorCloses() {
        // given
        List<DoorsAndLightsManager> allDoorsAndLightsManager = new ArrayList<>();
        allDoorsAndLightsManager.add(new LightEventManager());
        allDoorsAndLightsManager.add(new DoorEventManager());
        SmartHome smartHome = new SmartHome();
        Door door = new Door(true, "1");
        ArrayList<Door> doors = new ArrayList<>();
        doors.add(door);
        Room room = new Room(new ArrayList<Light>(), doors, "kitchen");
        smartHome.addRoom(room);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        for (DoorsAndLightsManager elem : allDoorsAndLightsManager){
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
        List<DoorsAndLightsManager> allDoorsAndLightsManager = new ArrayList<>();
        allDoorsAndLightsManager.add(new LightEventManager());
        allDoorsAndLightsManager.add(new DoorEventManager());
        allDoorsAndLightsManager.add(new HallDoorEventManager());
        SmartHome smartHome = new SmartHome();
        Door door = new Door(false, "1");
        ArrayList<Door> doors = new ArrayList<>();
        doors.add(door);
        Room room = new Room(new ArrayList<Light>(), doors, "kitchen");
        smartHome.addRoom(room);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        for (DoorsAndLightsManager elem : allDoorsAndLightsManager){
            elem.manage(smartHome, event);
        }
        // when
        boolean isSuccess = door.isOpen();
        // then
        assertTrue (isSuccess);
    }
}