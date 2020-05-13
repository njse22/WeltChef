package appmoviles.com.weltchef.control.adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import appmoviles.com.weltchef.R;

public class PlateImageHolder extends RecyclerView.ViewHolder {


    private ImageView imageView;
    public PlateImageHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imagePlate);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
