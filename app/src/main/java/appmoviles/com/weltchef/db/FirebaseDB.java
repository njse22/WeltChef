package appmoviles.com.weltchef.db;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.Message;
import appmoviles.com.weltchef.util.Constants;

public class FirebaseDB {

    private DatabaseReference databaseReference;
    private Query querySearch;
    private Object resultObject = null;

    public FirebaseDB() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.querySearch = null;
    }

    public Query getQuerySearch() {
        return querySearch;
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

    public void searchMessageContainer(String UserId){
        querySearch = databaseReference
                .child(Constants.FIREBASE_CHATS_BRANCH)
                .orderByChild("userIDChef")
                .equalTo(UserId);
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

    public String createId(String branch){
        return databaseReference.child(branch).push().getKey();
    }

    public String createMessageId(String messageContainerId){
        return databaseReference
                .child(Constants.FIREBASE_CHATS_BRANCH)
                .child(messageContainerId)
                .child("messages")
                .push()
                .getKey();
    }

    public void sendInfo(Object object, String id, String branch){
        databaseReference
                .child(branch)
                .child(id)
                .setValue(object);
    }

    public void sendMessage(String messageContainerId, String messageId, Message message){
        databaseReference
                .child(Constants.FIREBASE_CHATS_BRANCH)
                .child(messageContainerId)
                .child("messages")
                .child(messageId)
                .setValue(message);
    }

    public void addMenu(Menu menu, String chefID){
        databaseReference
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(Constants.FIREBASE_MENU_BRANCH)
                .child(chefID)
                .child(menu.getId())
                .setValue(menu);
        databaseReference
                .child(Constants.FIREBASE_MENU_BRANCH)
                .child(menu.getId())
                .setValue(menu);
    }

}
