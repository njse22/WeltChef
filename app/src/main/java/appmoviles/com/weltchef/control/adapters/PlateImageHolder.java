package appmoviles.com.weltchef.control.adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import appmoviles.com.weltchef.R;

public class PlateImageHolder extends RecyclerView.ViewHolder {

    private View view;
    private ImageView imageView;

    public PlateImageHolder(@NonNull View view) {
        super(view);
        this.view = view;
        imageView = view.findViewById(R.id.imagePlate);

    }

    public View getView() {
        return view;
    }

    public ImageView getImageView() {
        return imageView;
    }

}
