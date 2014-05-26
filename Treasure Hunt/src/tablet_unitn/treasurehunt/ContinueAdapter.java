package tablet_unitn.treasurehunt;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContinueAdapter extends ArrayAdapter<Map>{
	
	TextView name, checkpointsData, pathData, difficultyData, checkpoints, path, difficulty;
	Typeface roboto, robotoBold;
	
	public ContinueAdapter(Context context, int textViewResourceId, List<Map> objects) {
        super(context, textViewResourceId, objects);
        
        /* Non funzionante!!! Non cancellare!!!
        roboto = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Black.ttf");
        robotoBold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");*/
    }
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.continuelist, null);
        
        //view che conterranno i dati relativi all'avanzamento del giocatore
        name = (TextView) convertView.findViewById(R.id.continue_nomeMappa);
		/*checkpointsData = (TextView) convertView.findViewById(R.id.continue_checkpointsData);
		pathData = (TextView) convertView.findViewById(R.id.continue_pathData);
		difficultyData = (TextView) convertView.findViewById(R.id.continue_difficultyData);
		
		//Stardard view che non verranno modifiche durante l'inserimento delle info delle singole partite
		checkpoints = (TextView) convertView.findViewById(R.id.continue_checkpoints);
		path = (TextView) convertView.findViewById(R.id.continue_path);
		difficulty = (TextView) convertView.findViewById(R.id.continue_difficulty);
        */
		//scrittura delle info di avanzamento all'interno dei textview di ogni singola mappa
        Map map = getItem(position);
        name.setText(map.getName());
        /*checkpointsData.setText(map.getCheckpointsDone() + 	"/" + map.getCheckpointsTotal());
        pathData.setText(map.getPathDone() + "/" + map.getPathTotal() + " km");
        difficultyData.setText(map.getDifficulty());
        
        //impostazione del font
         Non funzionante!!! Non cancellare!!!
        name.setTypeface(robotoBold); checkpoints.setTypeface(robotoBold);
        path.setTypeface(robotoBold); difficulty.setTypeface(robotoBold);
        checkpointsData.setTypeface(roboto); pathData.setTypeface(roboto);
        difficultyData.setTypeface(roboto);*/
        
        return convertView;
    }
}
