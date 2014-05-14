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
        TextView name = (TextView)convertView.findViewById(R.id.nomeMappa);
		TextView distanza = (TextView)convertView.findViewById(R.id.distanza);
		TextView tempo_rimasto = (TextView)convertView.findViewById(R.id.tempoRimasto);
		ProgressBar avanzamaneto = (ProgressBar)convertView.findViewById(R.id.avanzamento);
        Map c = getItem(position);
        name.setText(c.getName());
		distanza.setText("13m");
		tempo_rimasto.setText("2h 13m");
		avanzamaneto.setProgress(0);
        return convertView;
    }
}
