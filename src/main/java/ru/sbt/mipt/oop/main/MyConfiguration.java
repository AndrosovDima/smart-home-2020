package ru.sbt.mipt.oop.main;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan
public class MyConfiguration {

    @Bean
    public SmartHome getSmartHome(SmartHomeGettable smartHomeGettable){
        return smartHomeGettable.loadHome();
    }

    @Bean
    public SensorEventsManager getEventHandler(List<EventHandler> managers, SmartHome smartHome){
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        managers.stream().map(eh -> new EventHandlerAdapter(smartHome, eh))
                .forEach(sensorEventsManager::registerEventHandler);
        return sensorEventsManager;
    }

    @Bean
    public EventHandler getLightEventManager(){
        return new DecoratorAlarm(new DecoratorSMS(new LightEventManager()));
    }

    @Bean
    public EventHandler getDoorEventManager(){
        return new DecoratorAlarm(new DoorEventManager());
    }

    @Bean
    public EventHandler getHallDoorEventManager(){
        return new DecoratorAlarm(new HallDoorEventManager());
    }
}
