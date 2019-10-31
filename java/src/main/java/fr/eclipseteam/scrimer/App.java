package fr.eclipseteam.scrimer;

import fr.eclipseteam.scrimer.api.model.Conv;
import fr.eclipseteam.scrimer.api.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {

    private Map<String, User> userList = new HashMap<>();
    private Map<String,Conv> convList = new HashMap<>();
    private static App instance;

    public App(){
        instance = this;
    }

    public Map<String, User> getActiveUserList() {
        return userList;
    }

    public Map<String,Conv> getConvList() {
        return convList;
    }

    public static App getInstance(){
        return instance == null? new App():instance;
    }
}
