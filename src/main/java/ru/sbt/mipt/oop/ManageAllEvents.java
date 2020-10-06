package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class ManageAllEvents implements AllEventsManagable{

    private final List<Managable> list;
    private final NextSensorEventGettable nextSensorEventGettable;

    public ManageAllEvents(List<Managable> list, NextSensorEventGettable nextSensorEventGettable) {
        this.list = list;
        this.nextSensorEventGettable = nextSensorEventGettable;
    }

    public void manageAllEvents(SmartHome smartHome) {
        SensorEvent event = nextSensorEventGettable.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (Managable elem : list){
                elem.manage(smartHome, event);
            }
            event = nextSensorEventGettable.getNextSensorEvent();
        }
    }
}
