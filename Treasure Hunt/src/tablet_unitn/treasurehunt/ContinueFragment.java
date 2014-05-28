package tablet_unitn.treasurehunt;

import java.util.LinkedList;
import java.util.List;

import tablet_unitn.checkInternet.MobileInternetConnectionDetector;
import tablet_unitn.checkInternet.WIFIInternetConnectionDetector;
import tablet_unitn.treasurehunt.Map;
import tablet_unitn.treasurehunt.ContinueAdapter;
import tablet_unitn.treasurehunt.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class ContinueFragment extends Fragment {

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

		// creating connection detector class instance
	    cd = new MobileInternetConnectionDetector(getActivity());
	    wc = new WIFIInternetConnectionDetector(getActivity());
	    // get Internet status
        isMobileConnectionExist = cd.checkMobileInternetConn();
        isWifiConnectionExist = wc.checkMobileInternetConn();
        Log.d("ciao123", "isMobileConnectionExist: "+isMobileConnectionExist);
        Log.d("ciao123", "isWifiConnectionExist: "+isWifiConnectionExist);
        if (isMobileConnectionExist||isWifiConnectionExist) {
        	
        	//get list from server
			List<Map> continue_list = new LinkedList<Map>();
			for (int i = 0; i < 30; i++) {
				Map map = new Map();
				map.setName("ciao2");
				map.SetID("1234");
				continue_list.add(map);
			}
			
	        ContinueAdapter continue_adapter = new ContinueAdapter(MainActivity.getAppContext(), R.layout.continuelist, continue_list);
	        continue_listView.setAdapter(continue_adapter);
	        
		} else {
            // Internet connection doesn't exist
        	Toast.makeText(MainActivity.getAppContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
		return continue_rootView;
	}
}
