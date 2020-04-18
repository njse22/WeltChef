package appmoviles.com.weltchef.db;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import appmoviles.com.weltchef.entity.User;

public class FirebaseDB  {

    private Query query;
    private DatabaseReference databaseReference;

    public FirebaseDB() {
        this.query = FirebaseDatabase.getInstance().getReference();
        this.databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    public Query getQuery() {
        return query;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public void searchInfo(){

    }

    public String createId(String branch){
        return databaseReference.child(branch).getKey();
    }

    public void sendUser(User user, String branch){
        databaseReference.child(branch).child(createId(branch)).setValue(user);
    }

    public void readInfo(String branch, String id){
        query = databaseReference.child(branch).child(id);

    }


}
