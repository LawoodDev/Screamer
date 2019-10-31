package fr.eclipseteam.scrimer.api.model;

import java.util.Date;

public class Message {
    private String content;
    private Date time;
    private boolean edited;
    public Message(String content){
        this.content = content;
        this.time = new Date();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
}
