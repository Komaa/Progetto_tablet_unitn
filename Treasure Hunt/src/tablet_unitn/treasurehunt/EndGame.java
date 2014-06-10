package tablet_unitn.treasurehunt;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class EndGame extends Activity{

	Typeface robotoThin, robotoCond, roboto, robotoReg;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgame);
        
        robotoReg = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Black.ttf");
        robotoThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        robotoCond = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Condensed.ttf");
        
        TextView points = (TextView) this.findViewById(R.id.points);
        points.setTypeface(robotoCond);
        points.setText("You earned\n"+ this.getIntent().getExtras().get(".points").toString() + " points");
        
        ImageButton collect = (ImageButton) this.findViewById(R.id.finish_game);
        collect.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
        	
        });
	}

}
