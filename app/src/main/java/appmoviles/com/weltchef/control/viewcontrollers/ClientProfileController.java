package appmoviles.com.weltchef.control.viewcontrollers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.Order;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.ImageryUtl;
import appmoviles.com.weltchef.view.ChatRoomActivity;
import appmoviles.com.weltchef.view.ClientProfileActivity;
import appmoviles.com.weltchef.view.LogingActivity;
import appmoviles.com.weltchef.view.MakeOrderActivity;
import appmoviles.com.weltchef.view.MapsActivity;
import appmoviles.com.weltchef.view.PhotoDialogFragment;

import static android.app.Activity.RESULT_OK;

public class ClientProfileController implements View.OnClickListener, ValueEventListener {

    private ClientProfileActivity view;
    private File photo;
    private User client;
    private Order order;
    private ArrayList<String> chefs;

    public ClientProfileController(ClientProfileActivity view) {
        this.view = view;
        this.client =  (User) view.getIntent().getExtras().get("user");
        this.view.getClientName().setText(client.getName());
        this.view.getSearchChef().setText(client.getEmail());
        this.view.getAskService().setText(client.getPhone());
        this.order = (Order) view.getIntent().getExtras().get("order");
        chefs = new ArrayList<>();
        view.getSeaechServiceFab().setOnClickListener(this);
        view.getSearchChefFab().setOnClickListener(this);
        view.getChatFab().setOnClickListener(this);
        view.getClientPicture().setOnClickListener(this);
        view.getChatBtn().setOnClickListener(this);

        FirebaseDatabase.getInstance().getApp().getOptions().getDatabaseUrl();

        ActivityCompat.requestPermissions(view, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        }, 0);

        if (order != null){
            for (Menu menu : order.getPlates())
                view.getOrderAdapter().addMenu(menu);
        }





//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(android.R.layout.simple_list_item_2, chefs);
//        view.getListChefs().setAdapter(adapter);

        Query listQueryChefs = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(client.getId())
                .child("menus")
                .limitToLast(10);
        listQueryChefs.addListenerForSingleValueEvent(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clientPicture:
                DialogFragment dialog = new PhotoDialogFragment(this);
                dialog.show(view.getSupportFragmentManager(), "photo_dialog");
                break;

            case R.id.takePhoto:
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photo = new File(view.getExternalFilesDir(null)+"/photo.png");
                Uri photoUri = FileProvider.getUriForFile(view, view.getPackageName(), photo);
                i.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                view.startActivityForResult(i, ImageryUtl.CAMERA_CALLBACK);
                break;

            case R.id.openGallery:
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                this.view.startActivityForResult(gallery, ImageryUtl.GALLERY_CALLBACK);
                break;

            case R.id.seaechServiceFab:
                Intent intentMakeOrder = new Intent(view, MakeOrderActivity.class);
                intentMakeOrder.putExtra("user", client);
                view.startActivity(intentMakeOrder);
                view.finish();
                break;

            case R.id.searchChefFab:
                Intent mapIntent = new Intent(view, MapsActivity.class);
                ActivityCompat.requestPermissions(view, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, 11);

                if(ContextCompat.checkSelfPermission(
                        this.view,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(
                        this.view,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    view.startActivity(mapIntent);
                }
                break;

            case R.id.chatFab:
                Intent intentChat = new Intent(view, ChatRoomActivity.class);
                intentChat.putExtra("user", (User) view.getIntent().getExtras().get("user"));
                view.startActivity(intentChat);
                break;

            case R.id.chatBtn:
                FirebaseAuth.getInstance().signOut();
                Intent intentLogout = new Intent(this.view, LogingActivity.class);
                this.view.startActivity(intentLogout);
                this.view.finish();
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == ImageryUtl.CAMERA_CALLBACK && resultCode == RESULT_OK){
            Bitmap image = BitmapFactory.decodeFile(photo.getPath());
            Bitmap thumbnail = Bitmap.createScaledBitmap(image, image.getWidth()/4, image.getHeight()/4, false);
            view.getClientPicture().setImageBitmap(thumbnail);
        }else if(requestCode == ImageryUtl.GALLERY_CALLBACK && resultCode == RESULT_OK){
            Uri uri = data.getData();
            photo = new File(ImageryUtl.getPath(this.view, uri));
            Bitmap image = BitmapFactory.decodeFile(photo.getPath());
            view.getClientPicture().setImageBitmap(image);
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Menu m = ds.getValue(Menu.class);
            chefs.add(m.getChefId());
        }
        view.updateListChef(chefs);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) { }
    
}
