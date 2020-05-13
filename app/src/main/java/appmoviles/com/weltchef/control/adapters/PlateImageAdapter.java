package appmoviles.com.weltchef.control.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import appmoviles.com.weltchef.R;

public class PlateImageAdapter extends RecyclerView.Adapter<PlateImageHolder> {

    private ArrayList<String> imagesUrls;
    private PlateImageHolder holder;

    public PlateImageAdapter(){
        imagesUrls = new ArrayList<>();
    }

    public ArrayList<String> getImagesUrls() {
        return imagesUrls;
    }

    public void setImagesUrls(ArrayList<String> imagesUrls) {
        this.imagesUrls = imagesUrls;
    }

    @NonNull
    @Override
    public PlateImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_plate_holder, parent);
        holder = new PlateImageHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlateImageHolder holder, int position) {
        ImageView imageView = holder.getImageView();
        String url = imagesUrls.get(position);
        Picasso.get().load(url).into(imageView);
    }

    @Override
    public int getItemCount() {
        return imagesUrls.size();
    }
}
