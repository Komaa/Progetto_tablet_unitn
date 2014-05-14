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
		TextView tempo_rimasto = (TextView)convertView.findViewById(R.id.new_tempoRimasto);
		TextView difficolta_txt = (TextView)convertView.findViewById(R.id.new_difficolta_txt);
		ProgressBar difficolta = (ProgressBar)convertView.findViewById(R.id.new_difficolta);
        Map c = getItem(position);
        name.setText("Nome: "+c.getName());
		distanza.setText("Distanza"+"13m");
		tempo_rimasto.setText("Tempo rimanente"+"2h 13m");
		Integer difficolta_val = c.getTrovati(); // da aggiornare
		if(difficolta_val<3)
			difficolta_txt.setText("Percorso facile");
		else if (difficolta_val>7)
			difficolta_txt.setText("Percorso difficile");
		else
			difficolta_txt.setText("Percorso medio");
		difficolta.setProgress(100*difficolta_val/10);
        return convertView;
    }
}
