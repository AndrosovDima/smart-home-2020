package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HallDoorEventManagerTest {

    @Test
    void testsManageForHallDoor_returnsTrue_whenAllLightTirnsOffIfDoorIsInHall() {
        // given
        List<DoorsAndLightsManager> allDoorsAndLightsManager = new ArrayList<>();
        allDoorsAndLightsManager.add(new LightEventManager());
        allDoorsAndLightsManager.add(new DoorEventManager());
        allDoorsAndLightsManager.add(new HallDoorEventManager());
        Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1")),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door(false, "2")),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door(true, "3")),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", true), new Light("8", false), new Light("9", true)),
                Arrays.asList(new Door(true, "4")),
                "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");
        for (DoorsAndLightsManager elem : allDoorsAndLightsManager){
            elem.manage(smartHome, event);
        }
        // when
        boolean isSuccess = true;
        for (Room room : smartHome.getRooms()){
            for (Light light : room.getLights()){
                if (light.isOn()){
                    isSuccess = false;
                    break;
                }
            }
        }
        // then
        assertTrue (isSuccess);
    }

    @Test
    void testsManageForHallDoor_returnsTrue_whenClosesHallDoorIfDoorIsInHall() {
        // given
        List<DoorsAndLightsManager> allDoorsAndLightsManager = new ArrayList<>();
        allDoorsAndLightsManager.add(new LightEventManager());
        allDoorsAndLightsManager.add(new DoorEventManager());
        allDoorsAndLightsManager.add(new HallDoorEventManager());
        Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1")),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door(false, "2")),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door(true, "3")),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", true), new Light("8", false), new Light("9", true)),
                Arrays.asList(new Door(true, "4")),
                "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");
        for (DoorsAndLightsManager elem : allDoorsAndLightsManager){
            elem.manage(smartHome, event);
        }
        // when
        boolean isSuccess = true;
        for (Room room : smartHome.getRooms()){
            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    if (door.isOpen()) {
                        isSuccess = false;
                        break;
                    }
                }
            }
        }
        // then
        assertTrue (isSuccess);
    }
}