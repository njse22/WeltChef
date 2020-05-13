package appmoviles.com.weltchef.control.viewcontrollers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.ImageryUtl;
import appmoviles.com.weltchef.view.CameraActivity;
import appmoviles.com.weltchef.view.ChatActivity;
import appmoviles.com.weltchef.view.ChatRoomActivity;
import appmoviles.com.weltchef.view.ChefProfileActivity;
import appmoviles.com.weltchef.view.CreatePlateActivity;
import appmoviles.com.weltchef.view.PhotoDialogFragment;

import static android.app.Activity.RESULT_OK;

public class ChefProfileController implements View.OnClickListener {

    private final static String TAG = "ChefProfileController>>>";

    private ChefProfileActivity view;
    private User chef;
    private File photo;

    @SuppressLint("LongLogTag")
    public ChefProfileController(ChefProfileActivity view) {
        this.view = view;
        Log.e(TAG, "ChefProfileController::view -> " + view);
        chef = (User) view.getIntent().getExtras().get("user");
        Log.e(TAG, "ChefProfileController::user -> " + chef);
        init();
    }

    public void init(){
        view.getWeltChef().setOnClickListener(this);
        view.getPhotochef().setOnClickListener(this);
        view.getChefPicture().setOnClickListener(this);
        view.getFabAddDish().setOnClickListener(this);
        //Pass data to Adapter
        ArrayList<String> newPlates = view.getPlateImageAdapter().getImagesUrls();
        newPlates.clear();
        //Replace with Data of FirebaseStorage
        newPlates.addAll(new ArrayList<>());
        view.getPlateImageAdapter().notifyDataSetChanged();

        ActivityCompat.requestPermissions(view, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        }, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chefPicture:
                DialogFragment dialog = new PhotoDialogFragment(this);
                dialog.show(view.getSupportFragmentManager(), Constants.PHOTO_PROFILE_TAG);
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

            case R.id.weltChefBtn:
                Intent intentChat = new Intent(view, ChatRoomActivity.class);
                intentChat.putExtra("user", (User) view.getIntent().getExtras().get("user"));
                view.startActivity(intentChat);
             break;

            case R.id.facebookBtn:
                break;
            case R.id.instagramBtn:
                break;
            case R.id.twitterBtn:
                break;

            case R.id.fabAddDish:
                Intent intentAddDish = new Intent(view, CreatePlateActivity.class);
                intentAddDish.putExtra("user", chef);
                view.startActivity(intentAddDish);
                view.finish();
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == ImageryUtl.CAMERA_CALLBACK && resultCode == RESULT_OK){
            Bitmap image = BitmapFactory.decodeFile(photo.getPath());
            Bitmap thumbnail = Bitmap.createScaledBitmap(image, image.getWidth()/4, image.getHeight()/4, false);
            view.getPhotochef().setImageBitmap(thumbnail);
        }else if(requestCode == ImageryUtl.GALLERY_CALLBACK && resultCode == RESULT_OK){
            Uri uri = data.getData();
            photo = new File(ImageryUtl.getPath(this.view, uri));
            Bitmap image = BitmapFactory.decodeFile(photo.getPath());
            view.getPhotochef().setImageBitmap(image);

        }
    }

    public void upDateMenus(){

    }

}