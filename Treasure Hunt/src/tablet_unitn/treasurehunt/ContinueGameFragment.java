package tablet_unitn.treasurehunt;

import java.util.LinkedList;
import java.util.List;

import tablet_unitn.treasurehunt.Map;
import tablet_unitn.treasurehunt.ContinueAdapter;

import tablet_unitn.treasurehunt.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ContinueGameFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_continue_game, container, false);
		ListView listView = (ListView)rootView.findViewById(R.id.listContinue);
		List list = new LinkedList();
        list.add(new Map("Mappa1"));
        ContinueAdapter adapter = new ContinueAdapter(MainActivity.getAppContext(), R.layout.continuelist, list);
        listView.setAdapter(adapter);
		return rootView;
	}
}
