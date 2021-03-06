package ru.sbt.mipt.oop.main;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeGetter implements SmartHomeGettable {
    // считываем состояние дома из файла
    public SmartHome loadHome() {
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        return smartHome;
    }
}
