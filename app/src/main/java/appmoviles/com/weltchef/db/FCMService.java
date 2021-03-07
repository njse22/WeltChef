package appmoviles.com.weltchef.db;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONObject;

import appmoviles.com.weltchef.entity.Message;
import appmoviles.com.weltchef.util.NotificationUtils;

public class FCMService extends FirebaseMessagingService {

    public static final String API_KEY = "";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        JSONObject object = new JSONObject(remoteMessage.getData());
        Gson gson = new Gson();
        Message message = gson.fromJson(object.toString(), Message.class);

        NotificationUtils.createNotificationChat(this, message.getBody());

    }
}
