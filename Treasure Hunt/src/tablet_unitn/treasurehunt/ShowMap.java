package tablet_unitn.treasurehunt;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ShowMap extends FragmentActivity implements LocationListener {
    GoogleMap googleMap;
    Location myLocation;
    TextView distance;
    
    // latitude and longitude
    double dante_latitude = 46.071546;
    double dante_longitude = 11.120449;
    LatLng piazzaDante = new LatLng(dante_latitude, dante_longitude);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmap);
        distance = (TextView) findViewById(R.id.distance);
        Button piazzaDante = (Button) findViewById(R.id.b_piazzaDante);
        piazzaDante.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addMarkerAndGo();
			}
		});

        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
 
        // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
 
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
 
        }else { // Google Play Services are available
 
            // Getting reference to the SupportMapFragment of activity_main.xml
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
 
            // Getting GoogleMap object from the fragment
            googleMap = fm.getMap();
 
            // Enabling MyLocation Layer of Google Map
            googleMap.setMyLocationEnabled(true);
 
            // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
 
            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();
 
            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);
 
            // Getting Current Location
            myLocation = locationManager.getLastKnownLocation(provider);
 
            if(myLocation!=null){
                onLocationChanged(myLocation);
            }
            locationManager.requestLocationUpdates(provider, 20000, 0, this);
        }
    }
    @Override
    public void onLocationChanged(Location location) {
  
        // Getting latitude of the current location
        double latitude = location.getLatitude();
 
        // Getting longitude of the current location
        double longitude = location.getLongitude();
 
        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);
 
        // The computed distance is stored in results[0].
        //If results has length 2 or greater, the initial bearing is stored in results[1].
        //If results has length 3 or greater, the final bearing is stored in results[2].
        float[] results = new float[1];
        Location.distanceBetween(latitude, longitude,
        		dante_latitude, dante_longitude, results);
        distance.setText("Distance: "+results[0]+" metres");
        /*
        // Showing the current location in Google Map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
 
        // Zoom in the Google Map
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
 	*/
        
        //Sposta la camera in una posizione
        CameraPosition myPosition = new CameraPosition.Builder().target(
                latLng).zoom(21).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
 
    }
 
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
    
    private void addMarkerAndGo() {
    	// create marker
        MarkerOptions marker = new MarkerOptions().position(piazzaDante).title("Piazza Dante");
         
        // adding marker
        googleMap.addMarker(marker);
        
        //Sposta la camera in una posizione
        CameraPosition c_piazzaDante = new CameraPosition.Builder().target(
                piazzaDante).zoom(21).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(c_piazzaDante));
    }
}