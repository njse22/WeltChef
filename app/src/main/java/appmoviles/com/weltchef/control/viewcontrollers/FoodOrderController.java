package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import java.util.Calendar;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FCMService;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.FCMMessage;
import appmoviles.com.weltchef.entity.Message;
import appmoviles.com.weltchef.entity.MessageContainer;
import appmoviles.com.weltchef.entity.Order;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.HTTPSWebUtilDomi;
import appmoviles.com.weltchef.util.NotificationUtils;
import appmoviles.com.weltchef.view.ClientProfileActivity;
import appmoviles.com.weltchef.view.FoodOrderActivity;

public class FoodOrderController implements View.OnClickListener, ValueEventListener, ChildEventListener {

    private final static String TAG = "FoodOrderController";

    private FoodOrderActivity view;
    private FirebaseDB firebaseDB;
    private Order order;
    private User chef;
    private User user;
    private MessageContainer messageContainer;
    private Gson gson;

    public FoodOrderController(FoodOrderActivity view) {
        this.view = view;
        this.firebaseDB = new FirebaseDB();
        this.gson = new Gson();
        this.messageContainer = new MessageContainer();
        init();
    }

    public void init(){
        this.user = (User) view.getIntent().getExtras().get("user");
        this.order = (Order) view.getIntent().getExtras().get("order");
        view.getCancel().setOnClickListener(this);
        view.getConfirm().setOnClickListener(this);
        int index = order.getPlates().size()-1;
        view.getNameDish().setText(order.getPlates().get(index).getName());
        view.getCost().setText(order.getTotalPrice()+"");

        firebaseDB.searchUserByid(order.getPlates().get(index).getChefId());
        firebaseDB.getQuerySearch().addListenerForSingleValueEvent(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:

                break;
            case R.id.confirm:

                Message message =  createMessage();
                FCMMessage fcm = new FCMMessage();
                fcm.setTo("/topics/"+order.getId());
                fcm.setData(message);
                String json = gson.toJson(fcm);

                new Thread(
                        ()-> {
                            HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                            utilDomi.POSTtoFCM(FCMService.API_KEY, json);
                        }
                ).start();

                Intent confirm = new Intent(view, ClientProfileActivity.class);
                confirm.putExtra("user", user);
                confirm.putExtra("order", order);
                view.startActivity(confirm);
                view.finish();
                break;
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (dataSnapshot.getChildrenCount() == 0) {

        } else {
            for (DataSnapshot data: dataSnapshot.getChildren()) {
                chef = (User)dataSnapshot.getValue(User.class);
                break;
            }
            view.getChef().setText(chef.getName());
        }
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        if (dataSnapshot.getChildrenCount() == 0){
            int index = order.getPlates().size()-1;
            String messageContainerID = firebaseDB.createId(Constants.FIREBASE_CHATS_BRANCH);
            MessageContainer messageContainer = new MessageContainer(messageContainerID, order.getPlates().get(index).getChefId(), user.getId());
            firebaseDB.sendInfo(messageContainer, messageContainer.getId(), Constants.FIREBASE_CHATS_BRANCH);
        }else {
            String id = dataSnapshot.child("id").getValue(String.class);
            String idChef = dataSnapshot.child("userIDChef").getValue(String.class);
            String idClient = dataSnapshot.child("userIDClient").getValue(String.class);
            messageContainer.setId(id);
            messageContainer.setUserIDChef(idChef);
            messageContainer.setUserIDClient(idClient);
        }
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) { }

    public Message createMessage(){
        int index = order.getPlates().size()-1;
        String messageContainerID = firebaseDB.createId(Constants.FIREBASE_CHATS_BRANCH);
        MessageContainer messageContainer = new MessageContainer(messageContainerID, order.getPlates().get(index).getChefId(), user.getId());
        firebaseDB.sendInfo(messageContainer, messageContainer.getId(), Constants.FIREBASE_CHATS_BRANCH);
        Log.e(TAG, "createMessage::exist -> " + messageContainer);
        String messageId = firebaseDB.createMessageId(messageContainer.getId());

        String body = "Plato: " +order.getPlates().get(index).getName() + "\n" +
                      "Valor: "+ order.getPlates().get(index).getPrice() + "\n" +
                (String) view.getIntent().getExtras().get("body").toString();

        Message message = new Message(
                Message.TYPE_TEXT,
                messageId,
                Message.ORDER_MESSAGE + "\n" + body,
                Calendar.getInstance().getTime().getTime(),
                user.getId()
        );

        firebaseDB.sendMessage(messageContainer.getId(), messageId, message);
        return message;
    }

    public void beforePause() {
        FirebaseMessaging.getInstance().subscribeToTopic(order.getId()).addOnCompleteListener(
                task ->{
                    if (task.isSuccessful()){
                        Log.e(TAG, "beforePause::topic -> true");
                    }
                }
        );
    }

    public void beforeResume() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(order.getId());
    }

}
