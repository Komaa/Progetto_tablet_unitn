package tablet_unitn.treasurehunt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import tablet_unitn.checkInternet.MobileInternetConnectionDetector;
import tablet_unitn.checkInternet.WIFIInternetConnectionDetector;
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
import android.widget.Toast;

public class NewFragment extends Fragment {
	
	List<Map> list_map=null;
	
	//Internet status flag
    Boolean isMobileConnectionExist = false;
    Boolean isWifiConnectionExist = false;
    // Connection detector class
    MobileInternetConnectionDetector cd;
    WIFIInternetConnectionDetector wc;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View new_rootView = inflater.inflate(R.layout.fragment_new_game, container, false);
		ListView new_listView = (ListView)new_rootView.findViewById(R.id.listNew);
		list_map = new ArrayList<Map>();

		// creating connection detector class instance
		cd = new MobileInternetConnectionDetector(getActivity());
	    wc = new WIFIInternetConnectionDetector(getActivity());
	    // get Internet status
        isMobileConnectionExist = cd.checkMobileInternetConn();
        isWifiConnectionExist = wc.checkMobileInternetConn();
        
		if (isMobileConnectionExist||isWifiConnectionExist) {
        
			//GET LIST OF MAPS
			getMaps();
			
			NewAdapter new_adapter = new NewAdapter(getActivity(), R.layout.newlist, list_map);
	        new_listView.setAdapter(new_adapter);
	        new_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				  @Override
				  public void onItemClick(AdapterView<?> parent, final View view,
				      int position, long id) {
					  Intent intent = new Intent(getActivity(),ShowMapDetails.class);
					  Map tmp = list_map.get(position);
					  
					  String ID= list_map.get(position).getID();
					  String name = list_map.get(position).getName();
					  int checkpoints = list_map.get(position).getCount();
					  int level = list_map.get(position).getLevel();
					  String time = ""; //variabile ancora da definire (AP)
					  String description = list_map.get(position).getDescription();
					  
					  //attraverso putExtra passo a ShowMapDetails.java le informazioni necessarie
					  //per compilare ogni campo di ogni mappa (AP)
					  intent.putExtra(".map_ID", ID);
					  intent.putExtra(".map_Name", name);
					  intent.putExtra(".map_Checkpoints", checkpoints);
					  String tt = ""+String.format("%.1f", tmp.getDist());
					  intent.putExtra(".map_Distance", tt);
					  intent.putExtra(".map_Level", level);
					  intent.putExtra(".map_Time", time);
					  intent.putExtra(".map_Description", description);
					  intent.putExtra(".usrName", MainActivity.user.getName());
					  startActivity(intent);
				  }
		    });
		} else {
            // Internet connection doesn't exist
        	Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
		return new_rootView;
	}
	
	@SuppressWarnings("unchecked")
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
