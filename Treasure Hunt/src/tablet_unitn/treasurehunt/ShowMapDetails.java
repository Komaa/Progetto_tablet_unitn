package tablet_unitn.treasurehunt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ShowMapDetails extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmapdetails);
        
        TextView title = (TextView)findViewById(R.id.map_name);
        TextView checkpoints = (TextView) findViewById(R.id.det_checkpoint);
        TextView distance = (TextView) findViewById(R.id.det_distance);
        TextView difficulty = (TextView) findViewById(R.id.det_difficulty);
        TextView timeLeft = (TextView) findViewById(R.id.det_time);
        TextView description = (TextView) findViewById(R.id.det_description);
        
        Button b_showMap = (Button) findViewById(R.id.show_map);
        
        description.setMovementMethod(new ScrollingMovementMethod()); //rende scrollabile la descrizione
        
        String ID = (String) this.getIntent().getExtras().get(MainActivity.getAppContext()+".map_ID");
        
        title.setText("ID mappa " + ID);
        
        b_showMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), ShowMap.class);
			    /*String[] mio = new String[2];
			    mio[0]= Integer.toString(123);
			    intent.putExtra(MainActivity.getAppContext()+".item",mio);*/
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
