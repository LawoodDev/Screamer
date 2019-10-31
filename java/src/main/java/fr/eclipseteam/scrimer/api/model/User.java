package fr.eclipseteam.scrimer.api.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {
    private String password;
    private String email;
    private String picUrl;
    private String uuid;
    private String userName;
    private ArrayList<String> convList = new ArrayList<>();
    private Map<String, Boolean> voteList = new HashMap<>();

    private ArrayList<String> matchList = new ArrayList<>();

    public User(String email, String name, String paswd, String pic){
        this.uuid = UUID.randomUUID().toString().replace("-", "");
        this.email = email;
        this.userName = name;
        this.password = paswd;
        this.picUrl = pic;
    }

    public String getEmail() {
        return email;
    }
    public String getPass(){
        return password;
    }
    public boolean comparePassword(String password){
        return password.equals(this.password);
    }
    public String getPicUrl() {
        return picUrl;
    }

    public Map<String, Boolean> getVoteList() {
        return voteList;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public ArrayList<String> getMatchList() {
        return matchList;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ArrayList<String> getConvList() {
        return convList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
