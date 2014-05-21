package tablet_unitn.checkInternet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
 
public class MobileInternetConnectionDetector {
 
    private Context _context;
 
    public MobileInternetConnectionDetector(Context context) {
        this._context = context;
    }
 
    public boolean checkMobileInternetConn() {
        //Create object for ConnectivityManager class which returns network related info
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //If connectivity object is not null
        if (connectivity != null) {
            //Get network info - Mobile internet access
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
 
            if (info != null) {
                //Look for whether device is currently connected to Mobile internet
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }
}
