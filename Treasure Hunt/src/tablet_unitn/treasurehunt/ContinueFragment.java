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

public class ContinueFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View continue_rootView = inflater.inflate(R.layout.fragment_continue_game, container, false);
		ListView continue_listView = (ListView)continue_rootView.findViewById(R.id.listContinue);
		List<Map> continue_list = new LinkedList<Map>();
		for (int i = 0; i < 30; i++) {
			Map map = new Map();
			map.setName("ciao");
			map.SetID("1234");
			continue_list.add(map);
		}
        ContinueAdapter continue_adapter = new ContinueAdapter(MainActivity.getAppContext(), R.layout.continuelist, continue_list);
        continue_listView.setAdapter(continue_adapter);
		return continue_rootView;
	}
}
