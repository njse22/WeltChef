package appmoviles.com.weltchef.db;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.entity.Client;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.Message;
import appmoviles.com.weltchef.entity.MessageContainer;
import appmoviles.com.weltchef.entity.Order;
import appmoviles.com.weltchef.entity.UsersManager;
import appmoviles.com.weltchef.util.Constants;

public class FirebaseDB  {

    private DatabaseReference databaseReference;
    private Query querySearch;

    public FirebaseDB() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.querySearch = null;
    }

    public Query getQuerySearch() {
        return querySearch;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public void getMenus(int type){
        querySearch = databaseReference
                .child(Constants.FIREBASE_MENU_BRANCH)
                .orderByChild("type")
                .equalTo(type);
    }

    public void searchOrder(String clientId){
        querySearch = databaseReference
                .child(Constants.FIREBASE_ORDER_BRANCH)
                .orderByChild("clientId")
                .equalTo(clientId);
    }

    public void searchUserByid(String id){
        querySearch = databaseReference
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(id);
    }

    public void searchChatByChef(String idChef){
        querySearch = databaseReference
                .child(Constants.FIREBASE_CHATS_BRANCH)
                .orderByChild(idChef);
    }

    public void searchChatByClient(String idClient){
        querySearch = databaseReference
                .child(Constants.FIREBASE_CHATS_BRANCH)
                .child(idClient);
    }

    public void searchUserByEmail(String email, String branch){
         querySearch = databaseReference
                .child(branch)
                .orderByChild("email")
                .equalTo(email);
    }

    public String createId(String branch){
        return databaseReference.child(branch).push().getKey();
    }

    public void sendInfo(Object object, String id, String branch){
        databaseReference
                .child(branch)
                .child(id)
                .setValue(object);
    }

    public void addMenu(Menu menu){
        databaseReference
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(Constants.FIREBASE_MENU_BRANCH)
                .child(menu.getId())
                .setValue(menu);
        databaseReference
                .child(Constants.FIREBASE_MENU_BRANCH)
                .child(menu.getId())
                .setValue(menu);
    }

    public void readInfo(String branch, String id){
        databaseReference.child(branch).child(id);
    }

}
