package appmoviles.com.weltchef.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.view.LogingActivity;
import appmoviles.com.weltchef.R;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDB firebaseDB = new FirebaseDB();

        /**Chef chef3 = new Chef(
                true,
                false,
                "Chef con 5 años de experiencia en comida gourmet",
                "pedro33@gmail.com",
                "pedro guzman",
                "Juan1234",
                "3165437798",
                firebaseDB.createId(Constans.FIREBASE_USER_BRANCH));

        Menu menu5 = new Menu(Menu.ITALIANA, 25000, "un plato italiano", "risoto", firebaseDB.createId(Constans.FIREBASE_MENU_BRANCH));
        menu5.setChefId(chef3.getId());

        FirebaseDatabase.getInstance().getReference()
                .child(Constans.FIREBASE_MENU_BRANCH)
                .child(menu5.getId())
                .setValue(menu5); **/

        Intent intent = new Intent(this, LogingActivity.class);
        this.startActivity(intent);


    }




}
