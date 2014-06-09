package tablet_unitn.treasurehunt;

import tablet_unitn.checkInternet.MobileInternetConnectionDetector;
import tablet_unitn.checkInternet.WIFIInternetConnectionDetector;
import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebView;
import android.widget.Toast;

public class Browser extends Activity {
	
	//Internet status flag
    Boolean isMobileConnectionExist = false;
    Boolean isWifiConnectionExist = false;
    
    // Connection detector class
    MobileInternetConnectionDetector cd;
    WIFIInternetConnectionDetector wc;
    
    private WebView webView;
    String URL="";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        
        URL = (String) this.getIntent().getExtras().get(".URL");
        
     // creating connection detector class instance
        cd = new MobileInternetConnectionDetector(getApplicationContext());
        wc = new WIFIInternetConnectionDetector(getApplicationContext());
        boolean isMobileConnectionExist = cd.checkMobileInternetConn();
        boolean isWifiConnectionExist = wc.checkMobileInternetConn();

        // check for Internet status
        if (isMobileConnectionExist||isWifiConnectionExist) {
        	webView = (WebView) findViewById(R.id.webview);
    		webView.loadUrl(URL);
        } else {
            // Internet connection doesn't exist
        	Toast.makeText(Browser.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }
        
    }    
}
