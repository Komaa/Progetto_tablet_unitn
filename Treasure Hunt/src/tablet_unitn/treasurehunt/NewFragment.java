package tablet_unitn.treasurehunt;

import java.util.LinkedList;
import java.util.List;

import tablet_unitn.treasurehunt.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class NewFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View new_rootView = inflater.inflate(R.layout.fragment_new_game, container, false);
		ListView new_listView = (ListView)new_rootView.findViewById(R.id.listNew);
		List<Map> new_list = new LinkedList<Map>();
		for (int i = 0; i < 30; i++) {
			new_list.add(new Map("Mappa"+i, i+5, i+10, "Medium"));
		}
        NewAdapter new_adapter = new NewAdapter(MainActivity.getAppContext(), R.layout.newlist, new_list);
        new_listView.setAdapter(new_adapter);
        new_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, final View view,
			      int position, long id) {
				//Toast.makeText(MainActivity.getAppContext(), "ciao!", Toast.LENGTH_LONG).show();
				  Intent intent = new Intent(MainActivity.getAppContext(),ShowMapDetails.class);
				  String[] mio = new String[2];
				  mio[0]= Integer.toString(position);
				  intent.putExtra(MainActivity.getAppContext()+".item",mio);
				  startActivity(intent);
			  }
	    });
		return new_rootView;
	}

}
