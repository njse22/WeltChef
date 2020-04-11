package appmoviles.com.weltchef.control.viewcontrollers;

import android.view.View;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.view.FoodOrderActivity;

public class FoodOrderController implements View.OnClickListener {

    private FoodOrderActivity view;

    public FoodOrderController(FoodOrderActivity view) {
        this.view = view;

        view.getCancel().setOnClickListener(this);
        view.getConfirm().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                break;
            case R.id.confirm:
                break;
        }
    }
}
