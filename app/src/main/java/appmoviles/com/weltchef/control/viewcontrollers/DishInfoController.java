package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;

import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.HTTPSWebUtilDomi;
import appmoviles.com.weltchef.view.DishInfoActivity;

public class DishInfoController implements ValueEventListener, RecyclerTouchListener.ClickListener {

    private DishInfoActivity activity;
    private File photo;
    private Menu menu;

    public DishInfoController(DishInfoActivity activity) {
        this.activity = activity;
        menu = (Menu) activity.getIntent().getExtras().get("menu");
        init();

    }

    public void init(){
        loadImage();
        activity.getNameChef().setText(menu.getName());
        activity.getEmail().setText(menu.getPrice());

        activity.getDescription().setText(menu.getDescription());

        if(menu.getChefId() != null){
            Query listQuery = FirebaseDatabase.getInstance().getReference()
                    .child(Constants.FIREBASE_MENU_BRANCH)
                    .child(menu.getChefId())
                    .limitToLast(10);

            listQuery.addListenerForSingleValueEvent(this);
        }

        activity.getListPlates().addOnItemTouchListener(new RecyclerTouchListener(
                activity.getApplicationContext(), activity.getListPlates(), this
        ));

    }

    public void loadImage(){
        activity.getChefPicture();
        String nameImage = menu.getId();
        photo = new File(activity.getExternalFilesDir(null)+ "/"+ nameImage);

        if (photo.exists()){
            loadImage(activity.getChefPicture(), photo);
        }else if (nameImage != null){
            FirebaseStorage.getInstance().getReference()
                    .child(Constants.FIREBASE_MENU_BRANCH)
                    .child(menu.getId())
                    .getDownloadUrl().addOnSuccessListener(
                    uri -> {
                        File f = new File(activity.getExternalFilesDir(null)+ "/"+ nameImage);
                        new Thread(
                                () -> {
                                    HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                                    utilDomi.saveURLImageOnFile(uri.toString(), f);
                                    activity.getChefPicture().post(
                                            () -> {
                                                loadImage(activity.getChefPicture(),f);
                                            }
                                    );
                                }
                        ).start();
                    }
            );
        }
    }

    private void loadImage(ImageButton chefPicture, File imageFile) {
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.toString());
        chefPicture.setImageBitmap(bitmap);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (dataSnapshot.getChildrenCount() == 0){
        }else {
            Menu menu = dataSnapshot.getValue(Menu.class);
            activity.getPlateImageAdapter().addMenu(menu);
            activity.getPlateImageAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) { }

    @Override
    public void onClick(View view, int position) { }

    @Override
    public void onLongClick(View view, int position) { }

}
