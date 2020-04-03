package appmoviles.com.weltchef;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class CameraController implements View.OnClickListener {

    public final static int CAMERA_CALLBACK = 1;
    private static final int GALLERY_CALLBACK = 2;
    private CameraActivity view;
    private File file;

    public CameraController(CameraActivity view) {
        this.view = view;

        ActivityCompat.requestPermissions(view, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 0);

        view.getGaleryBtn().setOnClickListener(this);
        view.getPhotoBtn().setOnClickListener(this);

        File root = new File(view.getExternalFilesDir(null) + "");
        Log.e(">>>", ""+ root);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.photoBtn:
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file = new File(view.getExternalFilesDir(null) + "/photo.png");
                Uri photoUri = FileProvider.getUriForFile(view,view.getPackageName(), file);
                i.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                view.startActivityForResult(i,CAMERA_CALLBACK);
                break;
            case R.id.galeryBtn:
                Intent gal = new Intent(Intent.ACTION_GET_CONTENT);
                gal.setType("image/*");
                this.view.startActivityForResult(gal, GALLERY_CALLBACK);
                break;
        }
    }

    public void onActivityResult(int requestCode, int requestCode1, Intent data) {
        if(requestCode == CAMERA_CALLBACK && requestCode1 == RESULT_OK){
            Bitmap image = BitmapFactory.decodeFile(file.getPath());
            Bitmap thumbnail = Bitmap.createScaledBitmap(
                    image,
                    image.getWidth()/4,
                    image.getHeight()/4,
                    false);

            view.getPhotoIV().setImageBitmap(thumbnail);
        } else if (requestCode == GALLERY_CALLBACK && requestCode1 == RESULT_OK){
            Uri uri = data.getData();
            file = new File(UtilDomi.getPath(this.view, uri));
            Bitmap image = BitmapFactory.decodeFile(file.toString());
            this.view.getPhotoIV().setImageBitmap(image);
        }
    }



}
