package ru.sbt.mipt.oop.main;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    @Bean
    public RemoteControlRegistry getRemoteControlRegistry(Collection<RemoteControl> remoteControllers) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControllers.forEach(remoteControlRegistry::registerRemoteControl);
        return remoteControlRegistry;
    }

    @Bean
    public RemoteControlImplForSmartHome getRemoteControlForSmartHome(Map<String, RemoteCommand> remoteCommands) {
        Map<String, String> coddingNamesOfCommands = Map.of(
                "remoteTurnOffAllLight", "A",
                "remoteCloseHallDoor", "B",
                "remoteTurnOnHallLight", "C",
                "remoteActivateSignaling", "D",
                "remoteAlarmActivation", "1",
                "remoteTurnOnAllLight", "2"
        );
        RemoteControlImplForSmartHome remoteController = new RemoteControlImplForSmartHome();
        remoteCommands.forEach((k, v) -> {
            remoteController.setButton(coddingNamesOfCommands.get(k), v);
        });
        return remoteController;
    }
}
