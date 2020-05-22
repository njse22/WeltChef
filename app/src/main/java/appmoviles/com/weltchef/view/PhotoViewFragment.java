package appmoviles.com.weltchef.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.storage.FirebaseStorage;

import java.io.File;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.interfaces.OnProfileRequest;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.HTTPSWebUtilDomi;

public class PhotoViewFragment extends DialogFragment{

    private View.OnClickListener listener;
    private Dialog dialog;
    private ImageView imageView;

    public PhotoViewFragment(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_photo_view, null);
        imageView = view.findViewById(R.id.picture);
        builder.setView(view);
        this.dialog = builder.create();
        return this.dialog;
    }

}
