package fr.eclipseteam.scrimer.api.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Conv {
    private Map<String,ArrayList<Message>> usersMessages = new HashMap<>();
    private String uuid;
    public Conv(User first, User second){
        this.uuid = UUID.randomUUID().toString().replace("-", "");
        this.usersMessages.put(first.getUuid(), new ArrayList<>());
        this.usersMessages.put(second.getUuid(), new ArrayList<>());
    }

    public void addUser(User us){
        this.usersMessages.put(us.getUuid(), new ArrayList<>());
    }
    public Message getLastMessage(){
        Message last=null;
        for(var userMes : usersMessages.values()) {
            for (var m : userMes) {
                if (last != null) {
                    if (m.getTime().after(last.getTime()))
                        last = m;
                } else {
                    last = m;
                }
            }
        }
        return last;
    }
    public String getUuid() {
        return uuid;
    }

    public Map<String, ArrayList<Message>> getUsersMessages() {
        return usersMessages;
    }

    public void sendMessage(User us, Message mes){
        this.usersMessages.get(us).add(mes);
    }
}
