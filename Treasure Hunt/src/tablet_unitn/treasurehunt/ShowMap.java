package tablet_unitn.treasurehunt;

import tablet_unitn.checkInternet.MobileInternetConnectionDetector;
import tablet_unitn.checkInternet.WIFIInternetConnectionDetector;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowMap extends FragmentActivity implements LocationListener, SensorEventListener {
    GoogleMap googleMap;
    Location myLocation;
    
    TextView distance;
    Button togglePosition;
    int toggle = 0;
    
    // latitude and longitude
    double dante_latitude = 46.071546;
    double dante_longitude = 11.120449;
    //LatLng piazzaDante = new LatLng(dante_latitude, dante_longitude);
    
    //Internet status flag
    Boolean isMobileConnectionExist = false;
    Boolean isWifiConnectionExist = false;
    
    // Connection detector class
    MobileInternetConnectionDetector cd;
    WIFIInternetConnectionDetector wc;
    
 // define the display assembly compass picture
    private ImageView image;
    // record the compass picture angle turned
    private float currentDegree = 0f;
    // device sensor manager
    private SensorManager mSensorManager;
    

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmap);
        distance = (TextView) findViewById(R.id.distance);
        togglePosition = (Button) findViewById(R.id.toggle_button_position);
        togglePosition.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		if(toggle == 0){
        			togglePosition.setText("Your\nPosition");
        			toggle = 1;
        		} else if(toggle == 1){
        			togglePosition.setText("Next Checkpoint");
        			toggle = 0;
        		}
        	}
        	
        });
        
        //Button piazzaDante = (Button) findViewById(R.id.b_piazzaDante);
        
        image = (ImageView) findViewById(R.id.imageViewCompass);
        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        
        // creating connection detector class instance
        cd = new MobileInternetConnectionDetector(getApplicationContext());
        wc = new WIFIInternetConnectionDetector(getApplicationContext());
        // get Internet status
        isMobileConnectionExist = cd.checkMobileInternetConn();
        isWifiConnectionExist = wc.checkMobileInternetConn();

        // check for Internet status
        if (!(isMobileConnectionExist||isWifiConnectionExist)) { //if not internet connection
        	Toast.makeText(ShowMap.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }
        
        /*piazzaDante.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addMarkerAndGo();
			}
		});*/

        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
 
        // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
 
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
 
        } else { // Google Play Services are available
 
            // Getting reference to the SupportMapFragment of activity_main.xml
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
 
            // Getting GoogleMap object from the fragment
            googleMap = fm.getMap();
 
            // Enabling MyLocation Layer of Google Map
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);
 
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
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            
         // Getting latitude of the current location
            double latitude = myLocation.getLatitude();
     
            // Getting longitude of the current location
            double longitude = myLocation.getLongitude();
     
            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);
     
            //Sposta la camera in una posizione
            CameraPosition myPosition = new CameraPosition.Builder().target(latLng).zoom(16).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
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
 
        //Sposta la camera in una posizione
        //CameraPosition myPosition = new CameraPosition.Builder().target(latLng).zoom(21).build();
        //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
        
        // The computed distance is stored in results[0].
        //If results has length 2 or greater, the initial bearing is stored in results[1].
        //If results has length 3 or greater, the final bearing is stored in results[2].
        float[] results = new float[1];
        Location.distanceBetween(latitude, longitude,
        		dante_latitude, dante_longitude, results);
        distance.setText("Next Checkpoint\n"+results[0]+" metres");
         
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
        /*MarkerOptions marker = new MarkerOptions().position(piazzaDante).title("Piazza Dante");
         
        // adding marker
        googleMap.addMarker(marker);
        
        //Sposta la camera in una posizione
        CameraPosition c_piazzaDante = new CameraPosition.Builder().target(
                piazzaDante).zoom(21).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(c_piazzaDante));*/
    }
    
    @Override
    protected void onResume() {
		super.onResume();
		// for the system's orientation sensor registered listeners
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
		SensorManager.SENSOR_DELAY_GAME);
    }
    
	@Override
	protected void onPause() {
		super.onPause();
		// to stop the listener and save battery
		mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        // get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]);
        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(
            currentDegree,
            -degree,
	        Animation.RELATIVE_TO_SELF, 0.5f,
	        Animation.RELATIVE_TO_SELF, 0.5f);
        // how long the animation will take place
    	ra.setDuration(210);
    	// set the animation after the end of the reservation status
    	ra.setFillAfter(true);
    	// Start the animation
    	image.startAnimation(ra);
    	currentDegree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	// not in use
    }

}