package ru.sbt.mipt.oop;

import java.util.List;

public class AllEventsManager implements AllEventsManagable{

    private final List<EventHandler> managersList;
    private final EventGenerator eventGenerator;

    public AllEventsManager(List<EventHandler> managersList, EventGenerator eventGenerator) {
        this.managersList = managersList;
        this.eventGenerator = eventGenerator;
    }

    public void manageAllEvents(SmartHome smartHome) {
        SensorEvent event = eventGenerator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler elem : managersList){
                elem.manage(smartHome, event);
            }
            event = eventGenerator.getNextSensorEvent();
        }
    }
}
