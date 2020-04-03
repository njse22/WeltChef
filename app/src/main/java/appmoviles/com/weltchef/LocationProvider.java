package appmoviles.com.weltchef;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class LocationProvider implements LocationListener {

    protected OnLocationReceivedListener listener;

    @Override
    public void onLocationChanged(Location location) {
        listener.OnLocationReceived(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

    public void setListener(OnLocationReceivedListener listener){
        this.listener = listener;
    }

    public interface OnLocationReceivedListener{
        void OnLocationReceived(Location location);
    }
}
