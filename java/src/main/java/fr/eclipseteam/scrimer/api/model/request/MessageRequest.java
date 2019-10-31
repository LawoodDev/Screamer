package fr.eclipseteam.scrimer.api.model.request;

import fr.eclipseteam.scrimer.api.model.Message;

public class MessageRequest extends Message {
    private String convId;
    private String userId;

    public MessageRequest(String userId, String convId, String content) {
        super(content);
        this.userId = userId;
        this.convId = convId;
    }

    public String getConvId() {
        return convId;
    }
    public String getUserId() {
        return userId;
    }
}
