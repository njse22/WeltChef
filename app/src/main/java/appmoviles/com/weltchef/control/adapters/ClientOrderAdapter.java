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

        ImageView imageMenu = menuList.findViewById(R.id.imageMenu);
        TextView nameMenu = menuList.findViewById(R.id.nameMenu);
        TextView chefMenu = menuList.findViewById(R.id.chefMenu);
        TextView priceMenu = menuList.findViewById(R.id.priceMenu);

        nameMenu.setText(menus.get(position).getName());
        chefMenu.setText(menus.get(position).getChefId());
        priceMenu.setText(menus.get(position).getPrice()+"");

        String nameImage = menus.get(position).getId();
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

                                imageMenu.post(
                                        () ->{
                                            loadImage(imageMenu, file);
                                        }
                                );
                            }
                    ).start();
                }
        );

        return menuList;
    }

    private void loadImage(ImageView imagePlate, File imageFile) {
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.toString());
        imagePlate.setImageBitmap(bitmap);
    }
}
