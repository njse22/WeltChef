package appmoviles.com.weltchef.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.util.Constants;

public class PhotoDialogFragment extends DialogFragment {

    private View.OnClickListener listener;
    private TextView titleTV;

    private Dialog dialog;

    public PhotoDialogFragment(View.OnClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.fragment_photo_dialog, null);

        Button takePhoto = (Button) v.findViewById(R.id.takePhoto);
        Button openGallery = (Button) v.findViewById(R.id.openGallery);
        titleTV = (TextView) v.findViewById(R.id.titleTV);

        takePhoto.setOnClickListener(listener);
        openGallery.setOnClickListener(listener);

        builder.setView(v);
        this.dialog = builder.create();
        return this.dialog;

    }

    public void changeTitle(String tag){
        if(tag.equals(Constants.PHOTO_PROFILE_TAG)){
            titleTV.setText("Cambiar foto de perfil");
        }
        else if(tag.equals(Constants.PHOTO_MENU_TAG)){
            titleTV.setText("Agregar foto del plato");
        }

    }


}
