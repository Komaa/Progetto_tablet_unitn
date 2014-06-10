package tablet_unitn.treasurehunt;

import tablet_unitn.checkInternet.MobileInternetConnectionDetector;
import tablet_unitn.checkInternet.WIFIInternetConnectionDetector;
import tablet_unitn.treasurehunt.R;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {
	
	User user=null;

	//Internet status flag
    Boolean isMobileConnectionExist = false;
    Boolean isWifiConnectionExist = false;
    // Connection detector class
    MobileInternetConnectionDetector cd;
    WIFIInternetConnectionDetector wc;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		user = MainActivity.user;		
		
		Typeface robotoThin = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Thin.ttf");
//		Typeface roboto = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Black.ttf");
//		int numGames = 3, numNewPubGames = 1, numNewPvtGames = 2;

		TextView welcome = (TextView) rootView.findViewById(R.id.t_wellcome);
		TextView infoContinue = (TextView) rootView.findViewById(R.id.t_stats);
		TextView infoNewPub = (TextView) rootView.findViewById(R.id.t_history);
		
		// creating connection detector class instance
		cd = new MobileInternetConnectionDetector(getActivity());
	    wc = new WIFIInternetConnectionDetector(getActivity());
	    // get Internet status
        isMobileConnectionExist = cd.checkMobileInternetConn();
        isWifiConnectionExist = wc.checkMobileInternetConn();
        
		if (isMobileConnectionExist||isWifiConnectionExist) {
        	//get user info from server
        	//user = getUserInfo(ID);
        	
			welcome.setTypeface(robotoThin);
			welcome.setText(welcome.getText() + " " + user.getName());
			
			infoContinue.setTypeface(robotoThin);
			user.mapsJoined.size();
			infoContinue.setText("You're playing " + user.getPartiteCorrenti() + ((user.getPartiteCorrenti() == 1) ? " game " : " games ") 
									+ "at the moment.\n\n" 
									+ "You have completed " + user.getPartiteCompletate() + ((user.getPartiteCompletate() == 1) ? " game " : " games."));
			
			infoNewPub.setTypeface(robotoThin);
			infoNewPub.setText("Your score is: \n" + user.getPoints() + " points");
		} else {
		    // Internet connection doesn't exist
			Toast.makeText(MainActivity.getAppContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
		}
		return rootView;
	}
	
	public User getUserInfo(String ID){
		String[] res = new String[6];
		//chiamata al server
		
		return new User(res[0], res[1], res[2], res[3], Integer.parseInt(res[4]), Integer.parseInt(res[5]));
	}
}
