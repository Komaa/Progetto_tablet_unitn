package tablet_unitn.checkInternet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
 
public class WIFIInternetConnectionDetector {
 
    private Context _context;
 
    public WIFIInternetConnectionDetector(Context context) {
        this._context = context;
    }
 
    public boolean checkMobileInternetConn() {
        //Create object for ConnectivityManager class which returns network related info
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //If connectivity object is not null
        if (connectivity != null) {
            //Get network info - WIFI internet access
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
 
            if (info != null) {
                //Look for whether device is currently connected to WIFI network
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }
}
