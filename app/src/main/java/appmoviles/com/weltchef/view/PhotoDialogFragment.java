package appmoviles.com.weltchef.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.interfaces.OnDialogListener;
import appmoviles.com.weltchef.control.viewcontrollers.ClientProfileController;

public class PhotoDialogFragment extends DialogFragment {

    private View.OnClickListener listener;

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

        takePhoto.setOnClickListener(listener);
        openGallery.setOnClickListener(listener);

        builder.setView(v);
        this.dialog = builder.create();
        return this.dialog;

    }

    @Override
    public void dismiss() {
        super.dismiss();
        this.dialog.dismiss();
    }
}
