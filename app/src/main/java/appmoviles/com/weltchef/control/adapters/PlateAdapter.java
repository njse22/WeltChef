package appmoviles.com.weltchef.control.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.HTTPSWebUtilDomi;

public class PlateAdapter extends BaseAdapter {

    private ArrayList<Menu> menus;


    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.image_plate_holder, null);

        ImageView imagePlate = view.findViewById(R.id.imagePlate);
        String nameImage = menus.get(position).getId();
        File imageFile = new File(parent.getContext().getExternalFilesDir(null)+ "/"+ nameImage);

        if(imageFile.exists()){
            loadImage(imagePlate, imageFile);
        }else {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            storage.getReference()
                    .child(Constants.FIREBASE_MENU_BRANCH)
                    .child(nameImage)
                    .getDownloadUrl().addOnSuccessListener(
                            uri -> {
                                File file = new File(parent.getContext().getExternalFilesDir(null)+"/"+nameImage);

                                new Thread(
                                        () -> {
                                            HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                                            utilDomi.saveURLImageOnFile(uri.toString(), file);

                                            view.post(
                                                    () ->{
                                                        loadImage(imagePlate, file);
                                                    }
                                            );
                                        }
                                ).start();
                            }
            );
        }

        return view;
    }

    private void loadImage(ImageView imagePlate, File imageFile) {
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.toString());
        imagePlate.setImageBitmap(bitmap);
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public void addMenu(Menu menu){
        menus.add(menu);
        notifyDataSetChanged();
    }

}
