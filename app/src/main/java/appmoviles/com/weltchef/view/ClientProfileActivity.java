package appmoviles.com.weltchef.view;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;

import android.os.Bundle;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import java.util.ArrayList;

import appmoviles.com.weltchef.R;

public class ClientProfileActivity extends AppCompatActivity  {

    private View dishLayau;
    private TextView clientName;
    private ImageButton clientPicture;
    private Button searchChef;
    private Button askService;
    private Button chatBtn;
    private NestedScrollView lastServices;
    private NestedScrollView likedChefs;
    private ClientOrderAdapter orderAdapter;
    private ListView listOrders;
    private ListView listChefs;
    private ArrayAdapter<String> chefs;
    private ClientProfileController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        dishLayau = findViewById(R.id.dishLayau);
        this.clientName = findViewById(R.id.clientName);
        searchChef = findViewById(R.id.searchChef);
        askService = findViewById(R.id.askService);
        chatBtn = findViewById(R.id.chatBtn);
        clientPicture = findViewById(R.id.clientPicture);
        lastServices = findViewById(R.id.lastServices);
        likedChefs = findViewById(R.id.likedChefs);
        listOrders = findViewById(R.id.listOrders);

        chefs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, new ArrayList<>());
        listChefs = findViewById(R.id.listChefs);
        listChefs.setAdapter(chefs);

        orderAdapter = new ClientOrderAdapter();
        listOrders.setAdapter(orderAdapter);


        controller = new ClientProfileController(this);
    }

    public TextView getClientName() {
        return clientName;
    }

    public Button getSearchChef() {
        return searchChef;
    }

    public Button getAskService() {
        return askService;
    }

    public Button getChatBtn() {
        return chatBtn;
    }

    public ImageButton getClientPicture() {
        return clientPicture;
    }

    public NestedScrollView getLastServices() {
        return lastServices;
    }

    public NestedScrollView getLikedChefs() {
        return likedChefs;
    }

    public ListView getListOrders() {
        return listOrders;
    }

    public ListView getListChefs() {
        return listChefs;
    }

    public ArrayAdapter<String> getChefs() {
        return chefs;
    }

    public ClientOrderAdapter getOrderAdapter() {
        return orderAdapter;
    }

    public void setOrderAdapter(ClientOrderAdapter orderAdapter) {
        this.orderAdapter = orderAdapter;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 11){
            if(ContextCompat.checkSelfPermission(
                    this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(
                    this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controller.onActivityResult(requestCode,resultCode, data);
    }

    public void updateListChef(ArrayList<String> chefs) {
        this.chefs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, chefs);
        listChefs.setAdapter(this.chefs);
    }
}
