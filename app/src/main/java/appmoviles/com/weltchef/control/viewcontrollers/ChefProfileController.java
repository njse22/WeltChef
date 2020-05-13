package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.service.carrier.CarrierMessagingService;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.ImageryUtl;
import appmoviles.com.weltchef.view.CameraActivity;
import appmoviles.com.weltchef.view.ChatActivity;
import appmoviles.com.weltchef.view.ChefProfileActivity;
import appmoviles.com.weltchef.view.PhotoDialogFragment;

import static android.app.Activity.RESULT_OK;

public class ChefProfileController implements View.OnClickListener{

    private ChefProfileActivity view;
    private User chef;
    private File photo;

    public ChefProfileController(ChefProfileActivity view) {
        this.view = view;
        //this.chef = (User) view.getIntent().getExtras().get("user");
        view.getChefPicture().setOnClickListener(this);
        //init();
    }

    public void init(){
        view.getNameChef().setText((String)view.getIntent().getExtras().get("name"));
        view.getTelephone().setText((String)view.getIntent().getExtras().get("phone"));
        view.getEmail().setText((String)view.getIntent().getExtras().get("email"));
        view.getDescription().setText((String)view.getIntent().getExtras().get("description"));
        view.getChefPicture().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        PhotoDialogFragment dialog = new PhotoDialogFragment(this);
        switch (v.getId()){
            case R.id.chefPicture:
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

           /** case R.id.weltChefBtn:
                Intent intentChat = new Intent(view, ChatActivity.class);
                intentChat.putExtra("user", chef);
                view.startActivity(intentChat);
                break;**/

            case R.id.facebookBtn:
                break;
            case R.id.instagramBtn:
                break;
            case R.id.twitterBtn:
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == ImageryUtl.CAMERA_CALLBACK && resultCode == RESULT_OK){
            Bitmap image = BitmapFactory.decodeFile(photo.getPath());
            Bitmap thumbnail = Bitmap.createScaledBitmap(image, image.getWidth()/4, image.getHeight()/4, false);
            view.getChefPicture().setImageBitmap(thumbnail);
        }else if(requestCode == ImageryUtl.GALLERY_CALLBACK && resultCode == RESULT_OK){
            Uri uri = data.getData();
            photo = new File(ImageryUtl.getPath(this.view, uri));
            Bitmap image = BitmapFactory.decodeFile(photo.getPath());
            Drawable d = Drawable.createFromPath(ImageryUtl.getPath(this.view, uri));
            view.getChefPicture().setImageBitmap(image);

        }
    }
}