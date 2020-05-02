package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.view.DishViewActivity;
import appmoviles.com.weltchef.view.MakeOrderActivity;

public class MakeOrderController implements View.OnClickListener, AdapterView.OnItemClickListener {

    private MakeOrderActivity activity;
    private ArrayAdapter<CharSequence> adapter;

    public MakeOrderController(MakeOrderActivity activity) {
        this.activity = activity;
        init();
    }

    public void init(){
        activity.getSearchService().setOnClickListener(this);
        adapter = ArrayAdapter.createFromResource(activity,R.array.types_array,R.layout.activity_make_order);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        activity.getSpinnerTypes().setAdapter(adapter);
        activity.getSpinnerTypes().setOnItemClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchService:
                Intent i = new Intent(activity, DishViewActivity.class);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
