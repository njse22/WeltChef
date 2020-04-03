package appmoviles.com.weltchef;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener  {

    private MapController mapController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapController = new MapController(googleMap);
        mapController.setActivity(this);
        mapController.init();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (!marker.equals(mapController.getMyLocation())) {
            Toast.makeText(this, " Usted está a: " +
                    mapController.calculateDistance(marker.getPosition().latitude,
                            mapController.getMyLocation().getPosition().latitude,
                            marker.getPosition().longitude,
                            mapController.getMyLocation().getPosition().longitude) + "de: " + marker.getTitle() , Toast.LENGTH_SHORT).show();
        } else {
            Geocoder geo = new Geocoder(this, Locale.getDefault());
            String dir = "";
            try {
                dir = geo.getFromLocation(mapController.getMyLocation().getPosition().latitude,
                        mapController.getMyLocation().getPosition().longitude,
                        1).get(0).getAddressLine(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Usted está en: " + dir, Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}