package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.text.Editable;
import android.view.View;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.UsersManager;
import appmoviles.com.weltchef.view.CreatePlateActivity;

public class CreatePlateController implements View.OnClickListener {

    private CreatePlateActivity activity;
    private UsersManager manager; // Temporal debe eliminarse y obtenerse con la interfaz OnProfilerequest

    public CreatePlateController(CreatePlateActivity activity) {
        this.activity = activity;
    }

    public void init(){
        activity.getPlateIB().setOnClickListener(this);
        activity.getSaveBtn().setOnClickListener(this);
        activity.getCancelBtn().setOnClickListener(this);

        // Temporal ..
        manager = new UsersManager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plateIB:

                break;

            case R.id.saveBtn:
                 String name = activity.getNamePlateET().getText().toString();
                 String nameChef = activity.getNameChefTV().getText().toString();
                 int price = Integer.parseInt(activity.getPriceET().getText().toString());
                 String type = activity.getTypeET().getSelectedItem().toString();
                 String description =  activity.getDescriptionET().getText().toString();
                 activity.getPlateIB();


                break;

            case R.id.cancelBtn:

                break;
        }

    }
}
