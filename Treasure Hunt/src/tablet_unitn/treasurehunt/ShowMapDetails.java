package tablet_unitn.treasurehunt;

import java.util.concurrent.ExecutionException;

import tablet_unitn.checkInternet.MobileInternetConnectionDetector;
import tablet_unitn.checkInternet.WIFIInternetConnectionDetector;
import tablet_unitn.dbmanager.JoinMap_db;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowMapDetails extends Activity {
	String usrName="",
			ID="";
	Typeface robotoThin, robotoCond, roboto, robotoReg;
	
	//Internet status flag
    Boolean isMobileConnectionExist = false;
    Boolean isWifiConnectionExist = false;
    
    // Connection detector class
    MobileInternetConnectionDetector cd;
    WIFIInternetConnectionDetector wc;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmapdetails);
        
        usrName = (String) this.getIntent().getExtras().get(".usrName");
        
        // creating connection detector class instance
        cd = new MobileInternetConnectionDetector(getApplicationContext());
        wc = new WIFIInternetConnectionDetector(getApplicationContext());
        
        robotoReg = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Black.ttf");
        robotoThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        robotoCond = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Condensed.ttf");
        
        TextView title = (TextView)findViewById(R.id.map_name);
        TextView checkpoints = (TextView) findViewById(R.id.det_checkpoint);
        TextView distance = (TextView) findViewById(R.id.det_distance);
        TextView difficulty = (TextView) findViewById(R.id.det_difficulty);
        TextView description = (TextView) findViewById(R.id.det_description);
        
        title.setTypeface(robotoCond);
        checkpoints.setTypeface(roboto); distance.setTypeface(roboto);
        description.setTypeface(robotoReg);
        
        ((TextView) findViewById(R.id.checkpoint)).setTypeface(robotoThin);
        ((TextView) findViewById(R.id.distance)).setTypeface(robotoThin);
        ((TextView) findViewById(R.id.difficulty)).setTypeface(robotoThin);
        
        Button b_showMap = (Button) findViewById(R.id.show_map);
        b_showMap.setTypeface(roboto);
        
        description.setMovementMethod(new ScrollingMovementMethod());
        
        //dati ricevuti da NewFragment.java tramite putExtra (AP)
        ID = (String) this.getIntent().getExtras().get(".map_ID");
        
        title.setText(""+ this.getIntent().getExtras().get(".map_Name"));
        checkpoints.setText(""+ this.getIntent().getExtras().get(".map_Checkpoints").toString());
        distance.setText(this.getIntent().getExtras().get(".map_Distance").toString()+" km");
        
        Integer level = (Integer) this.getIntent().getExtras().get(".map_Level");
        if(level.byteValue() == 1){
        	difficulty.setText("Easy");
        } else if(level.byteValue() == 2){
        	difficulty.setText("Medium");
        } else if(level.byteValue() == 3){
        	difficulty.setText("Hard");
        }
        
        description.setText(""+ this.getIntent().getExtras().get(".map_Description"));
        
        //title.setText("ID mappa " + ID);
        
        b_showMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				joinMap();
			}
		});
        
    }
    
    public void joinMap(){
    	// get Internet status
        isMobileConnectionExist = cd.checkMobileInternetConn();
        isWifiConnectionExist = wc.checkMobileInternetConn();
        
		if (isMobileConnectionExist||isWifiConnectionExist) {
			Intent intent = new Intent(getApplicationContext(), ShowMap.class);
		    String[] put = new String[2];
		    put[0] = ID; //id map
		    put[1] = MainActivity.user.getID(); //check if it works - id user
		    intent.putExtra(".map_info",put);
		    
		    //join map from server
		    JoinMap_db join = new JoinMap_db();
		    String check="";
		    try {
				check=join.execute(ID,usrName).get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}	
	    	if(Boolean.valueOf(check))	
	    		startActivity(intent);
	    	else
	    		Toast.makeText(getApplicationContext(), "An error occorred: "+check, Toast.LENGTH_SHORT).show();
		}else
			Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
