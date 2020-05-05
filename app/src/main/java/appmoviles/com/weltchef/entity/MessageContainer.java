package appmoviles.com.weltchef.entity;

import java.util.ArrayList;

public class MessageContainer {

    private String id;
    private ArrayList<Message> messages;

    public MessageContainer() {
    }

    public MessageContainer(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }



}
