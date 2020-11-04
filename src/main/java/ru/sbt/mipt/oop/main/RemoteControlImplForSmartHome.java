package ru.sbt.mipt.oop.main;

import rc.RemoteControl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteControlImplForSmartHome implements RemoteControl {
    private final Map<String, RemoteCommand> buttons = new HashMap<>();
    private final List<String> coddingNames = List.of("A", "B", "C", "D", "1", "2", "3", "4");;

    public void setButton(String buttonCode, RemoteCommand command) {
        if (coddingNames.contains(buttonCode)) {
            buttons.put(buttonCode, command);
        }
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (buttons.containsKey(buttonCode)) {
            buttons.get(buttonCode).execute();
        }
    }
}
