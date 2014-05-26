package tablet_unitn.treasurehunt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import tablet_unitn.dbmanager.NewMaps_db;
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
	
	List<Map> list_map=null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View new_rootView = inflater.inflate(R.layout.fragment_new_game, container, false);
		ListView new_listView = (ListView)new_rootView.findViewById(R.id.listNew);
		list_map=new ArrayList<Map>();

		//GET LIST OF MAPS
		getMaps();

		NewAdapter new_adapter = new NewAdapter(MainActivity.getAppContext(), R.layout.newlist, list_map);
        new_listView.setAdapter(new_adapter);
        new_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, final View view,
			      int position, long id) {
				  Intent intent = new Intent(MainActivity.getAppContext(),ShowMapDetails.class);
				  String ID= list_map.get(position).getID();
				  intent.putExtra(MainActivity.getAppContext()+".id",ID);
				  startActivity(intent);
			  }
	    });
		return new_rootView;
	}
	
	public void getMaps(){
    	NewMaps_db new_maps = new NewMaps_db();
    	try {
			list_map = new_maps.execute(list_map).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
