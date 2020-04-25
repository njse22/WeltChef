package appmoviles.com.weltchef.db;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.entity.UsersManager;
import appmoviles.com.weltchef.util.Constans;

public class FirebaseDB  {

    private DatabaseReference databaseReference;
    private Query querySearch;
    private UsersManager usersManager;

    public FirebaseDB() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.querySearch = null;
        this.usersManager = new UsersManager();
    }

    public Query getQuerySearch() {
        return querySearch;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public UsersManager getUsersManager() {
        return usersManager;
    }

    public void searchUserByEmail(String email, String branchChef, String branchClient){
        querySearch = databaseReference.child(branchChef).orderByChild("email").equalTo(email);
        Log.e(">>>", "query: " + querySearch);

        if (querySearch != null) {
            usersManager.setCunrrently(Constans.CHEF_INSTANCE);
            Log.e(">>>", "CHEF");
        }
        else {
            Log.e(">>>", "PAILA");
            querySearch = databaseReference.child(branchClient).orderByChild("email").equalTo(email);
            usersManager.setCunrrently(Constans.CLIENT_INSTANCE);
        }
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

    public void readInfo(String branch, String id){
        databaseReference.child(branch).child(id);
    }



}
