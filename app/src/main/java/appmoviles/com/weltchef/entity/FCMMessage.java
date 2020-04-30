package appmoviles.com.weltchef.entity;

public class FCMMessage  {

    private String to;
    private Message data;

    public FCMMessage() {
    }

    public FCMMessage(String to, Message data) {
        this.to = to;
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Message getData() {
        return data;
    }

    public void setData(Message data) {
        this.data = data;
    }

}
