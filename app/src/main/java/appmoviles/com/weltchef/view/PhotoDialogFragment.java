package appmoviles.com.weltchef.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import appmoviles.com.weltchef.R;

public class PhotoDialogFragment extends DialogFragment implements View.OnClickListener {



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.fragment_photo_dialog, null);

        Button takePhoto = (Button) v.findViewById(R.id.takePhoto);
        Button openGallery = (Button) v.findViewById(R.id.openGallery);

        takePhoto.setOnClickListener(this);
        openGallery.setOnClickListener(this);

        builder.setView(v);
        return builder.create();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.takePhoto:
                break;
            case R.id.openGallery:
                break;
        }
    }
}
