package appmoviles.com.weltchef.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageContainer implements Serializable {

    private String id;
    private ArrayList<Message> messages;
    private String userIDChef;
    private String userIDClient;

    public MessageContainer() {
    }

    public MessageContainer(String id, String userIDChef, String userIDClient) {
        this.id = id;
        this.userIDChef = userIDChef;
        this.userIDClient = userIDClient;
        this.messages = new ArrayList<>();
    }

    public MessageContainer(String id, ArrayList<Message> messages, String userIDChef, String userIDClient) {
        this.id = id;
        this.messages = messages;
        this.userIDChef = userIDChef;
        this.userIDClient = userIDClient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserIDChef() {
        return userIDChef;
    }

    public String getUserIDClient() {
        return userIDClient;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void setUserIDChef(String userIDChef) {
        this.userIDChef = userIDChef;
    }

    public void setUserIDClient(String userIDClient) {
        this.userIDClient = userIDClient;
    }



}
