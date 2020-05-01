package appmoviles.com.weltchef.db;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONObject;

import appmoviles.com.weltchef.entity.Message;
import appmoviles.com.weltchef.util.NotificationUtils;

public class FCMService extends FirebaseMessagingService {

    public static final String API_KEY = "AAAAaOBoGVw:APA91bEg1UZFQx4R75j6uesoB7_Qp2ZPcmRGnD1kzsz8raNBmwfcbYkw_Q51cLZg0EzQ9qoQAdrQVf_hlWf5h5pWAMxyeH5sfxQqTcMzD5l4NTx8-P3oEZsRuH5rjS4LNCRt6TaQxRyn";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        JSONObject object = new JSONObject(remoteMessage.getData());
        Gson gson = new Gson();
        Message message = gson.fromJson(object.toString(), Message.class);

        NotificationUtils.createNotificationChat(this, message.getBody());

    }
}
