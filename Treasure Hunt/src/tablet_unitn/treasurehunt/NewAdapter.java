package tablet_unitn.treasurehunt;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NewAdapter extends ArrayAdapter<Map>{
	
	Typeface roboto, robotoCond;
	
	public NewAdapter(Context context, int textViewResourceId, List<Map> objects) {
        super(context, textViewResourceId, objects);
        
        roboto = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
        robotoCond = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Condensed.ttf");
    }
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.newlist, null);
        
        TextView name = (TextView)convertView.findViewById(R.id.new_nomeMappa);
        TextView checkpoints = (TextView) convertView.findViewById(R.id.new_checkpoint);
		TextView distanza = (TextView)convertView.findViewById(R.id.new_distanza);
		TextView difficolta = (TextView)convertView.findViewById(R.id.new_difficolta);
		
		name.setTypeface(robotoCond);
		checkpoints.setTypeface(roboto);
		distanza.setTypeface(roboto);
		difficolta.setTypeface(roboto);
		
		//Nome, descrizione, livello e num punti
		Map c = getItem(position);
        name.setText(""+c.getName());
        checkpoints.setText(""+c.getCount());
        
        distanza.setText(c.getDist()+" km");
        
        int level = c.getLevel();
        if(level == 1){
        	difficolta.setText("Easy");
        } else if(level == 2){
        	difficolta.setText("Medium");
        } else if(level == 3){
        	difficolta.setText("Hard");
        }
        
        
        return convertView;
    }
}