package ru.sbt.mipt.oop.main;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan
public class MyConfiguration {

    @Bean
    public SmartHome smartHome(SmartHomeGettable smartHomeGettable){
        return smartHomeGettable.loadHome();
    }

    @Bean
    public SensorEventsManager eventHandler(Collection<EventHandlerAdapter> eventAdapters){
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        eventAdapters.forEach(sensorEventsManager::registerEventHandler);
        return sensorEventsManager;
    }

    @Bean
    public EventHandlerAdapter lightEventManager(SmartHome smartHome){
        return new EventHandlerAdapter(smartHome, new DecoratorAlarm(new DecoratorSMS(new LightEventManager())),
                mapForConvertingCCSensorEventsToSensorEvents());
    }

    @Bean
    public EventHandlerAdapter doorEventManager(SmartHome smartHome){
        return new EventHandlerAdapter(smartHome, new DecoratorAlarm(new DoorEventManager()),
                mapForConvertingCCSensorEventsToSensorEvents());
    }

    @Bean
    public EventHandlerAdapter hallDoorEventManager(SmartHome smartHome){
        return new EventHandlerAdapter(smartHome, new DecoratorAlarm(new HallDoorEventManager()),
                mapForConvertingCCSensorEventsToSensorEvents());
    }

    @Bean
    public EventHandlerAdapter signalingHandlerAdapter(AlarmManager eventHandler, SmartHome smartHome){
        return new EventHandlerAdapter(smartHome, eventHandler, mapForConvertingCCSensorEventsToSensorEvents());
    }

    @Bean
    public Map<String, SensorEventType> mapForConvertingCCSensorEventsToSensorEvents() {
        return new HashMap<>(
                Map.of("LightIsOn", SensorEventType.LIGHT_ON,
                        "LightIsOff", SensorEventType.LIGHT_OFF,
                        "DoorIsOpen", SensorEventType.DOOR_OPEN,
                        "DoorIsClosed", SensorEventType.DOOR_CLOSED,
                        "DoorIsLocked", SensorEventType.ALARM_ACTIVATE,
                        "DoorIsUnlocked", SensorEventType.ALARM_DEACTIVATE)
        );
    }
}
