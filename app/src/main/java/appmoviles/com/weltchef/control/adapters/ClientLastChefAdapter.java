package appmoviles.com.weltchef.control.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.Chef;

public class ClientLastChefAdapter extends BaseAdapter {

    private ArrayList<Chef> chefs;

    public ClientLastChefAdapter() {
        this.chefs = new ArrayList<>(10);
    }

    @Override
    public int getCount() {
        return chefs.size();
    }

    @Override
    public Object getItem(int position) {
        return chefs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.adapter_chefs, null);

        TextView chef = root.findViewById(R.id.chef);
        TextView email = root.findViewById(R.id.chef);
        TextView telefono = root.findViewById(R.id.chef);

        return root;
    }
}
