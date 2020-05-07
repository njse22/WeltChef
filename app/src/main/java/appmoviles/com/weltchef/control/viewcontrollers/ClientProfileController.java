package appmoviles.com.weltchef.control.viewcontrollers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.FirebaseDatabase;

import java.io.File;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.interfaces.OnProfileRequest;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.Order;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.ImageryUtl;
import appmoviles.com.weltchef.view.ClientProfileActivity;
import appmoviles.com.weltchef.view.FoodOrderActivity;
import appmoviles.com.weltchef.view.MakeOrderActivity;
import appmoviles.com.weltchef.view.MapsActivity;
import appmoviles.com.weltchef.view.PhotoDialogFragment;

import static android.app.Activity.RESULT_OK;

public class ClientProfileController implements View.OnClickListener, OnProfileRequest {

    private ClientProfileActivity view;
    private File photo;
    private User client;
    private Order order;
    private FoodOrderController subject;

    public ClientProfileController() {
    }

    public ClientProfileController(ClientProfileActivity view) {
        Log.e(">>>>", "call back -> client Controller" );
        this.view = view;
        this.client =  (User) view.getIntent().getExtras().get("user");
        this.subject = new FoodOrderController();
        subject.setListener(this);
        Log.e(">>>>", "call back -> listener == "+ subject );

        this.view.getClientName().setText(client.getName());

        view.getAskService().setOnClickListener(this);
        view.getSearchChef().setOnClickListener(this);
        view.getClientPicture().setOnClickListener(this);

        FirebaseDatabase.getInstance().getApp().getOptions().getDatabaseUrl();

        ActivityCompat.requestPermissions(view, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        }, 0);
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

            case R.id.askService:
                Intent intentMakeOrder = new Intent(view, MakeOrderActivity.class);
                intentMakeOrder.putExtra("user", client);
                view.startActivity(intentMakeOrder);
                break;

            case R.id.searchChef:
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
    public void onProfileResponse(int callBack, Object response) {
        Log.e(">>", "call back -> "+ callBack );
        switch (callBack){
            case Constants.UPDATE_ORDER:
                view.runOnUiThread(
                        () ->  {
                            order = (Order) response;
                            for (Menu menu : order.getPlates())
                                view.getOrderAdapter().addMenu(menu);
                        }
                );
                break;
        }
    }

}
