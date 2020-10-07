package ru.sbt.mipt.oop.main;

import java.util.List;

public class AllEventsManager implements AllEventsManagable{

    private final List<DoorsAndLightsManager> managersList;
    private final NextSensorEventGettable nextSensorEventGettable;

    public AllEventsManager(List<DoorsAndLightsManager> list, NextSensorEventGettable nextSensorEventGettable) {
        this.managersList = list;
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
