package tablet_unitn.treasurehunt;

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

public class ShowMapDetails extends Activity {
	
	Typeface robotoThin, robotoCond, roboto, robotoReg;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmapdetails);
        
        robotoReg = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Black.ttf");
        robotoThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        robotoCond = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Condensed.ttf");
        
        TextView title = (TextView)findViewById(R.id.map_name);
        TextView checkpoints = (TextView) findViewById(R.id.det_checkpoint);
        TextView distance = (TextView) findViewById(R.id.det_distance);
        TextView difficulty = (TextView) findViewById(R.id.det_difficulty);
        TextView timeLeft = (TextView) findViewById(R.id.det_time);
        TextView description = (TextView) findViewById(R.id.det_description);
        
        title.setTypeface(robotoCond);
        checkpoints.setTypeface(roboto); distance.setTypeface(roboto);
        difficulty.setTypeface(roboto); timeLeft.setTypeface(roboto);
        description.setTypeface(robotoReg);
        
        ((TextView) findViewById(R.id.checkpoint)).setTypeface(robotoThin);
        ((TextView) findViewById(R.id.distance)).setTypeface(robotoThin);
        ((TextView) findViewById(R.id.difficulty)).setTypeface(robotoThin);
        ((TextView) findViewById(R.id.time)).setTypeface(robotoThin);
        
        Button b_showMap = (Button) findViewById(R.id.show_map);
        b_showMap.setTypeface(roboto);
        
        description.setMovementMethod(new ScrollingMovementMethod());
        
        //dati ricevuti da NewFragment.java tramite putExtra (AP)
        final String ID = (String) this.getIntent().getExtras().get(".map_ID");
        
        title.setText(""+ this.getIntent().getExtras().get(".map_Name"));
        checkpoints.setText(""+ this.getIntent().getExtras().get(".map_Checkpoints").toString());
        
        Integer level = (Integer) this.getIntent().getExtras().get(".map_Level");
        if(level.byteValue() == 1){
        	difficulty.setText("Easy");
        } else if(level.byteValue() == 2){
        	difficulty.setText("Medium");
        } else if(level.byteValue() == 3){
        	difficulty.setText("Hard");
        }
        
        timeLeft.setText(""+ this.getIntent().getExtras().get(".map_Time"));
        description.setText(""+ this.getIntent().getExtras().get(".map_Description"));
        
        //title.setText("ID mappa " + ID);
        
        b_showMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), ShowMap.class);
			    String[] put = new String[2];
			    put[0] = ID; //id map
			    put[1] = MainActivity.user.getID(); //check if it works - id user
			    intent.putExtra(".map_info",put);
			    startActivity(intent);
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
