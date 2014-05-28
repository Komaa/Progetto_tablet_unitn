package tablet_unitn.treasurehunt;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class NewAdapter extends ArrayAdapter<Map>{
	
	public NewAdapter(Context context, int textViewResourceId, List<Map> objects) {
        super(context, textViewResourceId, objects);
    }
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.newlist, null);
        TextView name = (TextView)convertView.findViewById(R.id.new_nomeMappa);
		TextView distanza = (TextView)convertView.findViewById(R.id.new_distanza);
		TextView difficolta = (TextView)convertView.findViewById(R.id.new_difficolta);
		
		//Nome, descrizione, livello e num punti
		Map c = getItem(position);
        name.setText("Nome: "+c.getName());
		distanza.setText("Distanza"+"boh");
        return convertView;
    }
}
