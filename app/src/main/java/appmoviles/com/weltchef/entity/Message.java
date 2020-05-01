package appmoviles.com.weltchef.entity;

import java.io.Serializable;

public class Message implements Serializable {

    private String id;
    private String userId;
    private String body;
    private long timestamp;

    public Message() {
    }

    public Message(String id, String userId, String body, long timestamp) {
        this.id = id;
        this.userId = userId;
        this.body = body;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
