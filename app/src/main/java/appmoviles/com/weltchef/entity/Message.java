package appmoviles.com.weltchef.entity;

import java.io.Serializable;

public class Message implements Serializable {

    public final static int TYPE_TEXT = 0;
    public final static int TYPE_IMAGE = 1;

    private int type;
    private String id;
    private String body;
    private long timestamp;
    private String userId;

    public Message() {
    }

    public Message(int type,String id,String body ,long timestamp, String userId) {
        this.type = type;
        this.id = id;
        this.body = body;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
