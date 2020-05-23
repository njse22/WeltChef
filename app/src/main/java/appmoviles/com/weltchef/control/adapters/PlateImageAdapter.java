package appmoviles.com.weltchef.control.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.HTTPSWebUtilDomi;

public class PlateImageAdapter extends RecyclerView.Adapter<PlateImageHolder> {

    private ArrayList<Menu> menus;
    private PlateImageHolder holder;
    private Context context;
    private ImageView imagePlate;

    public PlateImageAdapter(Context context){
        this.context = context;
        this.menus = new ArrayList<>(10);
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void addMenu(Menu menu){
        menus.add(menu);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlateImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_plate_holder, parent, false);
        holder = new PlateImageHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlateImageHolder holder, int position) {
        imagePlate = holder.getImageView();
        String nameImage = menus.get(position).getId();
        File imageFile = new File(context.getExternalFilesDir(null)+ "/"+ nameImage);
        if(imageFile.exists()){
            loadImage(imagePlate, imageFile);
        }else if(nameImage != null){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            storage.getReference()
                    .child(Constants.FIREBASE_MENU_BRANCH)
                    .child(nameImage)
                    .getDownloadUrl().addOnSuccessListener(
                    uri -> {
                        File file = new File(context.getExternalFilesDir(null)+"/"+nameImage);

                        new Thread(
                                () -> {
                                    HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                                    utilDomi.saveURLImageOnFile(uri.toString(), file);

                                    holder.getView().post(
                                            () ->{
                                                loadImage(imagePlate, file);
                                            }
                                    );
                                }
                        ).start();
                    }
            );
        }
    }

    private void loadImage(ImageView imagePlate, File imageFile) {
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.toString());
        imagePlate.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }
}
