package appmoviles.com.weltchef.control.viewcontrollers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.ImageryUtl;
import appmoviles.com.weltchef.view.ChatRoomActivity;
import appmoviles.com.weltchef.view.ChefProfileActivity;
import appmoviles.com.weltchef.view.CreatePlateActivity;
import appmoviles.com.weltchef.view.DishViewActivity;
import appmoviles.com.weltchef.view.PhotoDialogFragment;

import static android.app.Activity.RESULT_OK;

public class ChefProfileController implements View.OnClickListener, ValueEventListener, RecyclerTouchListener.ClickListener {

    private final static String TAG = "ChefProfileController>>>";

    private ChefProfileActivity view;
    private User chef;
    private File photo;

    @SuppressLint("LongLogTag")
    public ChefProfileController(ChefProfileActivity view) {
        Log.e(TAG, "-> true");
        this.view = view;
        this.chef = (User) view.getIntent().getExtras().get("user");
        init();
    }

    /**
     * Inicializa todos los componentes a ser
     * Actuliza los campos de información acerca del chef
     */
    public void init() {

        //Set user fields
        view.getNameChef().setText(chef.getName());
        view.getEmail().setText(chef.getEmail());
        view.getTelephone().setText(chef.getEmail());
        view.getDescription().setText("El chef tiene un ranking de " + chef.getRanking());
        //Add needed listeers to buttons
        view.getChefPicture().setOnClickListener(this);
        view.getWeltChef().setOnClickListener(this);
        view.getFabAddDish().setOnClickListener(this);

        ActivityCompat.requestPermissions(view, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        }, 0);


        Query listQuery = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(chef.getId())
                .child("menus")
                .limitToLast(10);

        listQuery.addListenerForSingleValueEvent(this);

        view.getListPlates().addOnItemTouchListener(new RecyclerTouchListener(
                view.getApplicationContext(), view.getListPlates(), this
        ));
    }


    @Override
    public void onClick(View v) {
        PhotoDialogFragment dialog = new PhotoDialogFragment(this);
        switch (v.getId()) {
            case R.id.chefPicture:
                dialog.show(view.getSupportFragmentManager(), Constants.PHOTO_PROFILE_TAG);
                break;
            case R.id.takePhoto:
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photo = new File(view.getExternalFilesDir(null) + "/photo.png");
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
                intentAddDish.putExtra("user",  (User) view.getIntent().getExtras().get("user"));
                view.startActivity(intentAddDish);
                view.finish();
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImageryUtl.CAMERA_CALLBACK && resultCode == RESULT_OK) {
            Bitmap image = BitmapFactory.decodeFile(photo.getPath());
            Bitmap thumbnail = Bitmap.createScaledBitmap(image, image.getWidth() / 4, image.getHeight() / 4, false);
            view.getChefPicture().setImageBitmap(thumbnail);
        } else if (requestCode == ImageryUtl.GALLERY_CALLBACK && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            photo = new File(ImageryUtl.getPath(this.view, uri));
            Bitmap image = BitmapFactory.decodeFile(photo.getPath());
            Drawable d = Drawable.createFromPath(ImageryUtl.getPath(this.view, uri));
            view.getChefPicture().setImageBitmap(image);
        }
    }

    /**
     * Este metodo obtiene las URLs de las imagenes asociaddas con los platos de un chef específico.
     * Primero, busca todos los platos de un chef en FirebaseDatabase y luego obtiene las direcciones de las
     * imagenes almacenadas en FirebaseStorage.
     *
     * @param chefId Identificador del chef para obtener las imagenes
     * @return URL's de todos los platos realcionados con el chef
     */
    public ArrayList<String> getChefDishesImages(String chefId) {
        ArrayList<String> urls = new ArrayList<>();

        FirebaseStorage storage = FirebaseStorage.getInstance();

        Query listQuery = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(chefId)
                .child("menus")
                .limitToLast(10);

        listQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Menu menu = child.getValue(Menu.class);

                    //Consulta a FirebaseStorage para obtiner la dirección del recurso
                    storage.getReference()
                            .child(Constants.FIREBASE_MENU_BRANCH)
                            .child(menu.getId())
                            .getDownloadUrl()
                            .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    urls.add(uri.toString());
                                }
                            });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("Chef profile", "No hay registros de platos");
            }
        });

        return urls;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (dataSnapshot.getChildrenCount() == 0){
        }else {
            Menu menu = dataSnapshot.getValue(Menu.class);
            view.getPlateImageAdapter().addMenu(menu);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) { }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onLongClick(View view, int position) {

    }
}