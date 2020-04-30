package appmoviles.com.weltchef.control.viewcontrollers;


import android.view.View;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import appmoviles.com.weltchef.control.adapters.MessageAdapter;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.util.Constans;
import appmoviles.com.weltchef.view.ChatActivity;

public class ChatController implements View.OnClickListener, ValueEventListener {

    private ChatActivity activity;
    private Chef chef;
    private MessageAdapter adapter;
    private String clientEmail;
    private String chefEmail;
    private FirebaseDB firebaseDB;

    public ChatController(ChatActivity activity) {
        this.activity = activity;
        this.firebaseDB = new FirebaseDB();
        init();
    }

    public void init(){
        activity.getMessagesList().setAdapter(adapter);
        clientEmail = activity.getIntent().getExtras().getString("clientEmail");
        chefEmail = activity.getIntent().getExtras().getString("chefEmail");
        activity.getSendBtn().setOnClickListener(this);
        activity.getGalBtn().setOnClickListener(this);

        // get chef
        firebaseDB.getDatabaseReference()
                .child(Constans.FIREBASE_USER_BRANCH)
                .orderByChild("email").equalTo(chefEmail);

        firebaseDB.getQuerySearch().addListenerForSingleValueEvent(this);

        firebaseDB.getDatabaseReference()
        .child(Constans.FIREBASE_CHATS_BRANCH);


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getChildrenCount() == 0){

        } else {
            for (DataSnapshot coincidencia : dataSnapshot.getChildren()) {
                chef = coincidencia.getValue(Chef.class);
                break;
            }
        }

        adapter.setUserId(chef.getName());
        activity.getUsernameTV().setText(chef.getName());
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

}
