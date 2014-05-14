package tablet_unitn.treasurehunt;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ContinueAdapter extends ArrayAdapter<Map>{
	
	public ContinueAdapter(Context context, int textViewResourceId, List<Map> objects) {
        super(context, textViewResourceId, objects);
    }
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.continuelist, null);
        TextView name = (TextView)convertView.findViewById(R.id.continue_nomeMappa);
		TextView distanza = (TextView)convertView.findViewById(R.id.continue_distanza);
		TextView tempo_rimasto = (TextView)convertView.findViewById(R.id.continue_tempoRimasto);
		TextView avanzamento_txt = (TextView)convertView.findViewById(R.id.continue_avanzamento_txt);
		ProgressBar avanzamaneto = (ProgressBar)convertView.findViewById(R.id.continue_avanzamento);
        Map c = getItem(position);
        name.setText("Nome: "+c.getName());
		distanza.setText("Distanza: "+"13m");
		tempo_rimasto.setText("Tempo rimanente: "+"2h 13m");
		Integer fatti = 75,
				totale= 100;
		avanzamento_txt.setText("Trovati "+fatti+" su "+totale);
		avanzamaneto.setProgress(100*fatti/totale);
        return convertView;
    }
}
