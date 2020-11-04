package ru.sbt.mipt.oop.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import rc.RemoteControl;
import ru.sbt.mipt.oop.main.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RemoteCommandsTest {
//    private SmartHome smartHome;
//    private RemoteControl remoteControl;
    private SmartHome smartHome;
    private RemoteControl remoteControl;

    @BeforeEach
    void beforeEach(){
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        smartHome = context.getBean(SmartHome.class);
        remoteControl = context.getBean(RemoteControl.class);
    }

    @Test
    public void remoteTurnOffAllLight_returnsFalse_whenTurnsOffAllLight(){
        // when
        remoteControl.onButtonPressed("A");
        //then
        smartHome.execute(obj -> {
            if (obj instanceof Light) {
                Light light = (Light) obj;
                assertFalse(light.isOn());
            }
        });
    }

    @Test
    public void remoteCloseHallDoor_returnsFalse_whenHallDoorClosed(){
        // when
        remoteControl.onButtonPressed("B");
        //then
        smartHome.execute(o -> {
            if (!(o instanceof Room)) {
                return;
            }
            Room room = (Room) o;
            if ("hall".equals(room.getName())) {
                room.execute(c -> {
                    if (c instanceof Door) {
                        Door door = (Door) c;
                        assertFalse(door.isOpen());
                    }
                });
            }
        });
    }

}
