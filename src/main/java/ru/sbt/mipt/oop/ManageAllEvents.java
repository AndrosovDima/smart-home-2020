package ru.sbt.mipt.oop;

import java.util.List;

public class ManageAllEvents implements AllEventsManagable{

    private final List<DoorsAndLightsManager> managersList;
    private final NextSensorEventGettable nextSensorEventGettable;

    public ManageAllEvents(List<DoorsAndLightsManager> managersList, NextSensorEventGettable nextSensorEventGettable) {
        this.managersList = managersList;
        this.nextSensorEventGettable = nextSensorEventGettable;
    }

    public void manageAllEvents(SmartHome smartHome) {
        SensorEvent event = nextSensorEventGettable.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (DoorsAndLightsManager elem : managersList){
                elem.manage(smartHome, event);
            }
            event = nextSensorEventGettable.getNextSensorEvent();
        }
    }
}
