package appmoviles.com.weltchef.db;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.User;

public class FirebaseDB  {

    private Query query;
    private DatabaseReference databaseReference;

    public FirebaseDB() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.query = this.databaseReference;
    }

    public Query getQuery() {
        return query;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public void searchInfo(String id, String branch){

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
        query = databaseReference.child(branch).child(id);

    }


}
