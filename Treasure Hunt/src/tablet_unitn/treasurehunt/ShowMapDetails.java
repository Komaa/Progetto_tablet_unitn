package tablet_unitn.treasurehunt;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ShowMapDetails extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmapdetails);
        TextView title = (TextView)findViewById(R.id.map_name);
        String[] item = (String[]) this.getIntent().getExtras().get(MainActivity.getAppContext()+".item");
        title.setText("Mappa "+item[0]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
