package ru.sbt.mipt.oop.main;

import rc.RemoteControl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteControlImplForSmartHome implements RemoteControl {
    private final Map<String, RemoteCommand> buttons;

    public RemoteControlImplForSmartHome(Map<String, RemoteCommand> buttons) {
        this.buttons = buttons;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (buttons.containsKey(buttonCode)) {
            buttons.get(buttonCode).execute();
        }
    }
}
