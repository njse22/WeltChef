package appmoviles.com.weltchef.entity;

import java.io.Serializable;

public class Message implements Serializable {

    private String id;
    private String userIDChef;
    private String userIDClient;
    private String body;
    private long timestamp;

    public Message() {
    }

    public Message(String id, String userIDChef, String userIDClient,String body, long timestamp) {
        this.id = id;
        this.userIDChef = userIDChef;
        this.userIDClient = userIDClient;
        this.body = body;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getUserIDChef() {
        return userIDChef;
    }

    public void setUserIDChef(String userIDChef) {
        this.userIDChef = userIDChef;
    }

    public String getUserIDClient() {
        return userIDClient;
    }

    public void setUserIDClient(String userIDClient) {
        this.userIDClient = userIDClient;
    }

    public void setUserId(String userId) {
        this.userIDChef = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
