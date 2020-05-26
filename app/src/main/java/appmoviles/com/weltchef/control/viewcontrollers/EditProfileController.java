package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Timer;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.HTTPSWebUtilDomi;
import appmoviles.com.weltchef.util.ImageryUtl;
import appmoviles.com.weltchef.view.ChefProfileActivity;
import appmoviles.com.weltchef.view.EditProfileActivity;
import appmoviles.com.weltchef.view.PhotoDialogFragment;

import static android.app.Activity.RESULT_OK;

public class  EditProfileController implements View.OnClickListener {

    private final static String TAG = "EditProfileController";

    private EditProfileActivity view;
    private Chef user;
    private File picture;
    private Uri photoUri;
    private FirebaseDB database;
    private boolean imageChanged;

    public EditProfileController(EditProfileActivity view){
        this.view = view;
        this.imageChanged = false;
        this.database = new FirebaseDB();
        Log.e(TAG, "EditProfileController -> true");

        //Set buttons click listener
        view.getPicture().setOnClickListener(this);
        view.getSave().setOnClickListener(this);


        //Get user from activity intent
        user = (Chef) view.getIntent().getExtras().get("user");

        //Fill view elements
        view.getName().setText(user.getName());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.picture:
                PhotoDialogFragment dialog = new PhotoDialogFragment(this);
                dialog.show(view.getSupportFragmentManager(), Constants.PHOTO_PROFILE_TAG);
                break;
            case R.id.takePhoto:
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                picture = new File(view.getExternalFilesDir(null) + "/wc-"+user.getId()+".png");
                photoUri = FileProvider.getUriForFile(view, view.getPackageName(), picture);
                i.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                view.startActivityForResult(i, ImageryUtl.CAMERA_CALLBACK);
                imageChanged = true;
                break;
            case R.id.openGallery:
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                this.view.startActivityForResult(gallery, ImageryUtl.GALLERY_CALLBACK);
                imageChanged = true;
                break;
            case R.id.save:
                Log.e(TAG, "onClick::save -> true");
                verifyAndSave();
                break;
        }
    }

    private void verifyAndSave() {
        String password = view.getPassword().getText().toString();
        String passwordVerify = view.getPassword().getText().toString();
        String description = view.getDescriptionET().getText().toString();

        if(password.equals(passwordVerify)){
            user.setPassword(password);
            user.setDescription(description);

            user.setMenus(user.getMenus());
            FirebaseDatabase.getInstance().getReference()
                    .child(Constants.FIREBASE_USER_BRANCH)
                    .child(user.getId())
                    .setValue(user);

            if(imageChanged) {
                FirebaseStorage.getInstance().getReference()
                        .child(Constants.FIREBASE_USER_BRANCH)
                        .child(user.getId())
                        .putFile(photoUri);
            }
            FirebaseAuth.getInstance().getCurrentUser().updatePassword(password);
            Intent profile = new Intent(view, ChefProfileActivity.class);
            profile.putExtra("user", user);
            profile.putExtra("photo", picture);
            view.startActivity(profile);
            view.finish();
        }
        else {
            Toast.makeText(view, "Las contarseñas no coinciden", Toast.LENGTH_LONG);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImageryUtl.CAMERA_CALLBACK && resultCode == RESULT_OK) {
            Bitmap image = BitmapFactory.decodeFile(picture.getPath());
            Bitmap thumbnail = Bitmap.createScaledBitmap(image, image.getWidth() / 4, image.getHeight() / 4, false);
            view.getPicture().setImageBitmap(thumbnail);
        } else if (requestCode == ImageryUtl.GALLERY_CALLBACK && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            picture = new File(ImageryUtl.getPath(this.view, uri));
            Bitmap image = BitmapFactory.decodeFile(picture.getPath());
            Drawable d = Drawable.createFromPath(ImageryUtl.getPath(this.view, uri));
            view.getPicture().setImageBitmap(image);
        }
    }
}
