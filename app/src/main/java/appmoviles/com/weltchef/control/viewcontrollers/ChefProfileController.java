package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.FirebaseDatabase;

import java.io.File;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.ImageryUtl;
import appmoviles.com.weltchef.view.CameraActivity;
import appmoviles.com.weltchef.view.ChefProfileActivity;
import appmoviles.com.weltchef.view.PhotoDialogFragment;

import static android.app.Activity.RESULT_OK;

public class ChefProfileController implements View.OnClickListener {

    private ChefProfileActivity view;
    private User user;
    private File photo;

    public ChefProfileController(ChefProfileActivity view) {
        this.view = view;

        user = (User) view.getIntent().getExtras().get("user");

        view.getNameChef().setText(user.getName());
        view.getEmail().setText(user.getEmail());
        view.getTelephone().setText(user.getPhone());
        view.getWhatsapp().setOnClickListener(this);
        view.getFacebook().setOnClickListener(this);
        view.getInstagram().setOnClickListener(this);
        view.getTwitter().setOnClickListener(this);

<<<<<<< HEAD
     //   FirebaseDatabase.getInstance().getApp().getOptions().getDatabaseUrl(); // ??
        init();
    }

    public void init(){
        view.getNameChef().setText((String)view.getIntent().getExtras().get("name"));
        view.getTelephone().setText((String)view.getIntent().getExtras().get("phone"));
        view.getEmail().setText((String)view.getIntent().getExtras().get("email"));
        view.getDescription().setText((String)view.getIntent().getExtras().get("description"));

        view.getPhotochef().setOnClickListener(this);

    }

=======
        FirebaseDatabase.getInstance().getApp().getOptions().getDatabaseUrl(); // ??
    }

    
>>>>>>> 128d17ae2a7bcdfd86ade50e17e236d8c58c6211
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chefPicture:
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
            case R.id.whatsappBtn:
                break;
            case R.id.facebookBtn:
                break;
            case R.id.instagramBtn:
                break;
            case R.id.twitterBtn:
                break;
        }
    }

<<<<<<< HEAD
            case R.id.imageView:
               // Intent intent = new Intent(this.view, ChefProfileActivity.class);
               // this.view.startActivity(intent);
                break;


=======
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
>>>>>>> 128d17ae2a7bcdfd86ade50e17e236d8c58c6211
        }
    }
}
