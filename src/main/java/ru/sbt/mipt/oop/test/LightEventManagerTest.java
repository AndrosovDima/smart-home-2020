package ru.sbt.mipt.oop.test;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.main.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LightEventManagerTest {

    @Test
    void testsManageForLight_returnsTrue_whenLightTurnsOff() {
        // given
        List<DoorsAndLightsManager> allDoorsAndLightsManager = new ArrayList<>();
        allDoorsAndLightsManager.add(new LightEventManager());
        allDoorsAndLightsManager.add(new DoorEventManager());
        allDoorsAndLightsManager.add(new HallDoorEventManager());
        SmartHome smartHome = new SmartHome();
        Light light = new Light("1", true);
        ArrayList<Light> lights = new ArrayList<>();
        lights.add(light);
        Room room = new Room(lights, new ArrayList<Door>(), "kitchen");
        smartHome.addRoom(room);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        for (DoorsAndLightsManager elem : allDoorsAndLightsManager){
            elem.manage(smartHome, event);
        }
        // when
        boolean isSuccess = !(light.isOn());
        // then
        assertTrue (isSuccess);
    }

    @Test
    void testsManageForLight_returnsTrue_whenLightTurnsOn() {
        // given
        List<DoorsAndLightsManager> allDoorsAndLightsManager = new ArrayList<>();
        allDoorsAndLightsManager.add(new LightEventManager());
        allDoorsAndLightsManager.add(new DoorEventManager());
        SmartHome smartHome = new SmartHome();
        Light light = new Light("1", false);
        ArrayList<Light> lights = new ArrayList<>();
        lights.add(light);
        Room room = new Room(lights, new ArrayList<Door>(), "kitchen");
        smartHome.addRoom(room);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        for (DoorsAndLightsManager elem : allDoorsAndLightsManager){
            elem.manage(smartHome, event);
        }
        // when
        boolean isSuccess = light.isOn();
        // then
        assertTrue (isSuccess);
    }
}