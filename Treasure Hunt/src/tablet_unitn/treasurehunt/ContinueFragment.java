package tablet_unitn.treasurehunt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import tablet_unitn.checkInternet.MobileInternetConnectionDetector;
import tablet_unitn.checkInternet.WIFIInternetConnectionDetector;
import tablet_unitn.dbmanager.ContinueMaps_db;
import tablet_unitn.dbmanager.NewMaps_db;
import tablet_unitn.treasurehunt.Map;
import tablet_unitn.treasurehunt.ContinueAdapter;
import tablet_unitn.treasurehunt.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ContinueFragment extends Fragment {
	
	List<Map> list_map = null;
	User user = null;

	//Internet status flag
    Boolean isMobileConnectionExist = false;
    Boolean isWifiConnectionExist = false;
    // Connection detector class
    MobileInternetConnectionDetector cd;
    WIFIInternetConnectionDetector wc;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View continue_rootView = inflater.inflate(R.layout.fragment_continue_game, container, false);
		ListView continue_listView = (ListView)continue_rootView.findViewById(R.id.listContinue);
		list_map = new ArrayList<Map>();
		user = MainActivity.user;

		// creating connection detector class instance
	    cd = new MobileInternetConnectionDetector(getActivity());
	    wc = new WIFIInternetConnectionDetector(getActivity());
	    // get Internet status
        isMobileConnectionExist = cd.checkMobileInternetConn();
        isWifiConnectionExist = wc.checkMobileInternetConn();
        Log.d("ciao123", "isMobileConnectionExist: "+isMobileConnectionExist);
        Log.d("ciao123", "isWifiConnectionExist: "+isWifiConnectionExist);
        if (isMobileConnectionExist||isWifiConnectionExist) {
        	
        	getMaps(user.getName());
        	
			
	        ContinueAdapter continue_adapter = new ContinueAdapter(MainActivity.getAppContext(), R.layout.continuelist, list_map);
	        continue_listView.setAdapter(continue_adapter);
	        continue_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        	@Override
				  public void onItemClick(AdapterView<?> parent, final View view,
				      int position, long id) {
					  Intent intent = new Intent(getActivity(),ShowMapDetails.class);
					  
					  String ID= list_map.get(position).getID();
					  String name = list_map.get(position).getName();
					  int checkpoints = list_map.get(position).getCount();
					  int distance = 0; //variabile ancora da definire (AP)
					  int level = list_map.get(position).getLevel();
					  String time = ""; //variabile ancora da definire (AP)
					  String description = list_map.get(position).getDescription();
					  
					  //attraverso putExtra passo a ShowMapDetails.java le informazioni necessarie
					  //per compilare ogni campo di ogni mappa (AP)
					  intent.putExtra(".map_ID", ID);
					  intent.putExtra(".map_Name", name);
					  intent.putExtra(".map_Checkpoints", checkpoints);
					  intent.putExtra(".map_Distance", distance);
					  intent.putExtra(".map_Level", level);
					  intent.putExtra(".map_Time", time);
					  intent.putExtra(".map_Description", description);
					  intent.putExtra(".usrName", MainActivity.user.getName());
					  startActivity(intent);
				  }
		    });
	        
		} else {
            // Internet connection doesn't exist
        	Toast.makeText(MainActivity.getAppContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
		return continue_rootView;
	}
	public void getMaps(String user){
    	ContinueMaps_db continue_maps = new ContinueMaps_db(user);
    	try {
			list_map = continue_maps.execute(list_map).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
