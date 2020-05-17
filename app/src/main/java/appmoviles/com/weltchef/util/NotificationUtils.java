package appmoviles.com.weltchef.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

import appmoviles.com.weltchef.R;

public class NotificationUtils {

    public static final String CHANNEL_ID = "WeltChef";
    public static final String CHANNEL_MESSAGE = "Messages";
    public static final String CHANNEL_ORDERS = "Orders";

    public static final int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;
    public static int consecutiveMessage = 1;
    public static int consecutiveOrder = 1;

    public static void createNotificationChat(Context context, String message){
        NotificationManager manager = (NotificationManager)
                context.getSystemService(context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channelChat = new NotificationChannel(CHANNEL_ID, CHANNEL_MESSAGE, CHANNEL_IMPORTANCE);
            manager.createNotificationChannel(channelChat);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Nuevo Mensaje")
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher);

        manager.notify(consecutiveMessage, builder.build());
        consecutiveMessage++;
    }

    public static void createNotificationOrder(Context context, String message){
        NotificationManager manager = (NotificationManager)
                context.getSystemService(context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channelOrders = new NotificationChannel(CHANNEL_ID, CHANNEL_ORDERS, CHANNEL_IMPORTANCE);
            manager.createNotificationChannel(channelOrders);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Nueva Orden")
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher);

        manager.notify(consecutiveOrder, builder.build());
        consecutiveOrder++;
    }


}
