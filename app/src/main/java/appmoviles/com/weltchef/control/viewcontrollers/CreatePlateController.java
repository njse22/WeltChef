package appmoviles.com.weltchef.control.viewcontrollers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.entity.UsersManager;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.ImageryUtl;
import appmoviles.com.weltchef.view.ChefProfileActivity;
import appmoviles.com.weltchef.view.CreatePlateActivity;
import appmoviles.com.weltchef.view.PhotoDialogFragment;
import appmoviles.com.weltchef.view.PlateViewFragment;

import static android.app.Activity.RESULT_OK;

public class CreatePlateController implements View.OnClickListener {

    private final static String TAG = "CreatePlateController";

    private CreatePlateActivity activity;
    private FirebaseDB firebaseDB;
    private User chef;
    private Menu menu;
    private Uri photoUri;
    private File photo;

    public CreatePlateController(CreatePlateActivity activity) {
        this.activity = activity;
        this.firebaseDB = new FirebaseDB();
        this.menu = new Menu();
        this.chef = (User)activity.getIntent().getExtras().get("user");
        init();
    }

    public void init(){
        activity.getPlateIB().setOnClickListener(this);
        activity.getSaveBtn().setOnClickListener(this);
        activity.getCancelBtn().setOnClickListener(this);
        activity.getCancelBtn().setVisibility(View.GONE);
        activity.getNameChefTV().setText(chef.getName());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plateIB:
                DialogFragment dialogFragment = new PlateViewFragment(this);
                dialogFragment.show(activity.getSupportFragmentManager(), Constants.PHOTO_MENU_TAG);
                break;

            case R.id.takePhoto:
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photo = new File(activity.getExternalFilesDir(null)+"/photo.png");
                photoUri = FileProvider.getUriForFile(activity, activity.getPackageName(), photo);
                i.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                activity.startActivityForResult(i, ImageryUtl.CAMERA_CALLBACK);
                break;

            case R.id.openGallery:
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                this.activity.startActivityForResult(gallery, ImageryUtl.GALLERY_CALLBACK);
                break;

            case R.id.saveBtn:
                int type =  activity.getTypeSpinner().getSelectedItemPosition()+1;
                int price = Integer.parseInt(activity.getPriceET().getText().toString());
                String description =  activity.getDescriptionET().getText().toString();
                String name = activity.getNamePlateET().getText().toString();
                String nameChef = activity.getNameChefTV().getText().toString();
                activity.getPlateIB();
                String id = firebaseDB.createId(Constants.FIREBASE_MENU_BRANCH);
                menu = new Menu(type, price, description, name, id, chef.getId());
                if (photoUri != null){
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    storage.getReference()
                            .child(Constants.FIREBASE_MENU_BRANCH)
                            .child(menu.getId())
                            .putFile(photoUri);
                }
                firebaseDB.addMenu(menu, chef.getId());
                Intent intentCreatePlate = new Intent(activity, ChefProfileActivity.class);
                intentCreatePlate.putExtra("user", chef);
                intentCreatePlate.putExtra("menu", menu);
                activity.startActivity(intentCreatePlate);
                activity.finish();
                break;

            case R.id.cancelBtn:

                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ImageryUtl.CAMERA_CALLBACK && resultCode == RESULT_OK){
            Bitmap image = BitmapFactory.decodeFile(photo.getPath());
            Bitmap thumbnail = Bitmap.createScaledBitmap(image, image.getWidth(), image.getHeight(), false);
            activity.getPlateIB().setImageBitmap(thumbnail);
        }else if(requestCode == ImageryUtl.GALLERY_CALLBACK && resultCode == RESULT_OK){
            Uri uri = data.getData();
            photo = new File(ImageryUtl.getPath(this.activity, uri));
            Bitmap image = BitmapFactory.decodeFile(photo.getPath());
            activity.getPlateIB().setImageBitmap(image);
        }
    }
}
