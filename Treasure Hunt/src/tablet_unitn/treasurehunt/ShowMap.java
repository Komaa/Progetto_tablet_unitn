package tablet_unitn.treasurehunt;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutionException;

import tablet_unitn.checkInternet.MobileInternetConnectionDetector;
import tablet_unitn.checkInternet.WIFIInternetConnectionDetector;
import tablet_unitn.dbmanager.ContinueMaps_db;
import tablet_unitn.dbmanager.GetPoints_db;
import tablet_unitn.dbmanager.WikiInfo_db;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.content.Intent;
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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowMap extends FragmentActivity implements LocationListener, SensorEventListener {
	
	List<Goal> listGoals=null;
	List<Map> list_map=null;
	Map map;
	
    GoogleMap googleMap;
    Location myLocation;
    
    TextView distance;
    Button togglePosition;
    int toggle = 0; //0=your position, 1=next goal
    int checkpointNumber = 0; //la variabile serve per capire quanti checkpoint della mappa sono stati fatti
    int indexRightAnswer = 0;
    boolean check=false;
    
    //next latitude and longitude
    double offset = 10; //0.0001 area di ritrovamento del punto 0 -> esattamente sopra 
    double next_lat = 0;
    double next_lng = 0;
    double my_lat = 1000; //deve essere distante dal marker
    double my_lng = 1000; //deve essere distante dal marker
    
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
        
        //dati ricevuti da ShowMapDetails.java tramite putExtra (AP)
        String[] IDs = (String[]) this.getIntent().getExtras().get(".map_info");
        
//        Log.d("ciao1", "map id: "+IDs[0]);
//        Log.d("ciao1", "user Name: "+IDs[1]);
        
        //GET MAP AND LIST OF POINTS
        getMaps(IDs[0], IDs[1]); //mapID e userName
        Log.d("ciao1", "map: "+map);
		getPoints(IDs[0]);
					
		Log.d("ciao1", "listGoals: "+listGoals);
        distance = (TextView) findViewById(R.id.distance);
        togglePosition = (Button) findViewById(R.id.toggle_button_position);
        togglePosition.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		if(toggle == 0){
        			togglePosition.setText("Your\nPosition");
        			LatLng latLng = new LatLng(my_lat, my_lng);
        			CameraPosition cam = new CameraPosition.Builder().target(
        					latLng).zoom(18).build();
        	        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam));
        			toggle = 1;
        		} else if(toggle == 1){
        			togglePosition.setText("Next Checkpoint");
        			LatLng latLng = new LatLng(next_lat, next_lng);
        			CameraPosition cam = new CameraPosition.Builder().target(
        					latLng).zoom(18).build();
        	        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam));
        			toggle = 0;
        		}
        	}
        });
        
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
            googleMap.getUiSettings().setMyLocationButtonEnabled(false); //nasconde il pulsante in altro a destra
 
            // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
 
            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();
 
            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);
 
            // Getting Current Location
            myLocation = locationManager.getLastKnownLocation(provider);

            locationManager.requestLocationUpdates(provider, 20000, 0, this);
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
         // Getting latitude of the current location
            double latitude = myLocation.getLatitude();
     
            // Getting longitude of the current location
            double longitude = myLocation.getLongitude();
           
            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);
            
            //Sposta la camera nella mia posizione
            CameraPosition myPosition = new CameraPosition.Builder().target(latLng).zoom(16).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
            Log.d("ciao1", "qui arrivo");
            //aggiungi punti e vai!!!
            Goal tmp = listGoals.get(map.getTappe()); //get the next goal
            checkpointNumber = map.getTappe();
           
            if(tmp != null){
            	next_lat=tmp.lat;
            	next_lng=tmp.lng;
            	addPos(tmp.lat, tmp.lng);
            }else{
            	Toast.makeText(ShowMap.this, "Map error. No next goal found.", Toast.LENGTH_LONG).show();
            }
            
        }
       
    }
    
    @SuppressWarnings("unchecked")
	public void getMaps(String mapID, String usrName){
    	ContinueMaps_db continue_maps = new ContinueMaps_db(usrName);
    	list_map=new ArrayList<Map>();
    	try {
			list_map = continue_maps.execute(list_map).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	int q=0;
    	for (Map mm : list_map) {
			if(mm.getID().equals(mapID)){
				this.map=mm;
				q++;
			}
		}
    	if(q==0){ //map not found
    		Toast.makeText(ShowMap.this, "Map error. No map found.", Toast.LENGTH_LONG).show();
    		map=new Map();
    	}
	}
    
    @SuppressWarnings("unchecked")
	public void getPoints(String mapID){
    	GetPoints_db get_points = new GetPoints_db(mapID);
    	listGoals = new ArrayList<Goal>();
    	try {
			listGoals = get_points.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Override
    public void onLocationChanged(Location location) {
  
        // Getting latitude and longitude of the current location
        my_lat = location.getLatitude();
        my_lng = location.getLongitude();
        
        //get wikipedia info
        WikiInfo_db info_db = new WikiInfo_db(my_lat, my_lng);
        List<Wiki> info = new ArrayList<Wiki>();
        try {
			info = info_db.execute().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
        
        if(((next_lat-offset) < my_lat && my_lat < (next_lat+offset)) && ((next_lng-offset) < my_lng  && my_lng < (next_lng+offset))){
        	
        	if(!check){
        	Intent intent = new Intent(getApplicationContext(), CheckpointQuestion.class);
         	intent.putExtra(".question", listGoals.get(checkpointNumber).getText());
         	
         	Enumeration<String> enumKey = listGoals.get(checkpointNumber).response.keys();
         	int i=1;
         	while(enumKey.hasMoreElements()) {
         	    String key = enumKey.nextElement();
         	    Boolean val = listGoals.get(checkpointNumber).response.get(key);
         	    intent.putExtra(".answer" + i, key);
         	    intent.putExtra(".isCorrect" +i, val);
         	    i++;
         	}
         	check=true;
         	this.startActivityForResult(intent, 1);
        	
        	next_lat=listGoals.get(checkpointNumber).getLat();
        	next_lng=listGoals.get(checkpointNumber).getLng();
        
        	}
        	
        }
        
        // Creating a LatLng object for the current location
        //LatLng latLng = new LatLng(latitude, longitude);
 
        //Sposta la camera in una posizione
        //CameraPosition myPosition = new CameraPosition.Builder().target(latLng).zoom(21).build();
        //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
        
        // The computed distance is stored in results[0].
        //If results has length 2 or greater, the initial bearing is stored in results[1].
        //If results has length 3 or greater, the final bearing is stored in results[2].
        float[] results = new float[1];
        Location.distanceBetween(my_lat, my_lng, next_lat, next_lng, results);
        if(listGoals.size()!=0)
        	distance.setText("Next Checkpoint\n"+String.format("%.1f", results[0])+" metres");
        else
        	distance.setText("No points");
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
    
    private void addPos(double lat, double lng) {
    	// create marker
    	LatLng llg = new LatLng(lat, lng);
    	
    	MarkerOptions marker = new MarkerOptions().position(llg);
        // adding marker
        googleMap.addMarker(marker);
        
        
        /*
        //Sposta la camera in una posizione
        CameraPosition c_piazzaDante = new CameraPosition.Builder().target(
                piazzaDante).zoom(21).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(c_piazzaDante));*/
    }
    
    @SuppressWarnings("deprecation")
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
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                googleMap.clear();
            	check=false;
            	checkpointNumber++;
            	next_lat=listGoals.get(checkpointNumber).getLat();
            	next_lng=listGoals.get(checkpointNumber).getLng();
                Goal tmp = listGoals.get(checkpointNumber); //get the next goal
                addPos(tmp.getLat(), tmp.getLng());
            	
                String result=data.getStringExtra("result");
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

}
