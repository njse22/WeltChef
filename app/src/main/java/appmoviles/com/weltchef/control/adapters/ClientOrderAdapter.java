package appmoviles.com.weltchef.control.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.HTTPSWebUtilDomi;

public class ClientOrderAdapter extends BaseAdapter {

    private ArrayList<Menu> menus;

    public ClientOrderAdapter() {
        menus = new ArrayList<>(10);
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
        notifyDataSetChanged();
    }

    public void addMenu(Menu menu){
        this.menus.add(menu);
        notifyDataSetChanged();
    }

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
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View menuList = inflater.inflate(R.layout.adapter_order, null);

        TextView nameMenu = menuList.findViewById(R.id.nameMenu);
        TextView priceMenu = menuList.findViewById(R.id.priceMenu);

        nameMenu.setText("Plato: "+ menus.get(position).getName());
        priceMenu.setText("Valor por plato: "+ menus.get(position).getPrice());

        ImageView imageMenu = menuList.findViewById(R.id.imageMenu);
        String nameImage = menus.get(position).getId();
        File file = new File(parent.getContext().getExternalFilesDir(null)+"/"+nameImage);

        if(file.exists()){
            loadImage(imageMenu, file);
        }else {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            storage.getReference().child(Constants.FIREBASE_CHATS_BRANCH).child(nameImage)
                    .getDownloadUrl().addOnSuccessListener(
                    uri -> {
                        //Almecenar en archivo
                        File f = new File( parent.getContext().getExternalFilesDir(null) + "/" + nameImage );
                        new Thread(
                                () -> {
                                    HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                                    utilDomi.saveURLImageOnFile(uri.toString(),f);
                                    //runOnUiThread --> Activity
                                    // post -> view
                                    menuList.post(
                                            () -> {
                                                // ejecuta en el principal
                                                loadImage(imageMenu, f);
                                            }
                                    );
                                }
                        ).start();
                    }
            );
        }
        return menuList;
    }

    private void loadImage(ImageView imageMenu, File file) {
        Bitmap bitmap = BitmapFactory.decodeFile(file.toString());
        imageMenu.setImageBitmap(bitmap);
    }
}
