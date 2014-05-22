
package tablet_unitn.treasurehunt;

import tablet_unitn.checkInternet.MobileInternetConnectionDetector;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	EditText mail,psw;
	Button login, register;
	
	//Internet status flag
    Boolean isMobileConnectionExist = false;
    //Internet status flag
    Boolean isWifiConnectionExist = false;
    
    // Connection detector class
    MobileInternetConnectionDetector cd;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.login);
        
        Typeface robotoThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface robotoBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        
        // creating connection detector class instance
        cd = new MobileInternetConnectionDetector(getApplicationContext());
        
        TextView t_mail = (TextView) findViewById(R.id.t_mail);
        t_mail.setTypeface(robotoThin);
        TextView t_psw = (TextView) findViewById(R.id.t_psw);
        t_psw.setTypeface(robotoThin);
        TextView title_app = (TextView) findViewById(R.id.app_title);
        title_app.setTypeface(robotoBold);
        
        mail = (EditText) findViewById(R.id.login_mail);
        psw = (EditText) findViewById(R.id.login_psw);
        login = (Button) findViewById(R.id.login_button);
        register = (Button) findViewById(R.id.register_button);
        
        login.setTypeface(robotoThin);
        register.setTypeface(robotoThin);
        
        login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// get Internet status
		        isMobileConnectionExist = cd.checkMobileInternetConn();

		        // check for Internet status
		        if ((isMobileConnectionExist)||true) {
		            // Internet Connection exists
		        	Toast.makeText(Login.this, "Your device has mobile internet", Toast.LENGTH_SHORT).show();
		        	login();
		        } else {
		            // Internet connection doesn't exist
		        	Toast.makeText(Login.this, "Your device doesn't have mobile internet", Toast.LENGTH_SHORT).show();
//		            showAlertDialog(this, "No Internet Connection",
//		                    "Your device doesn't have mobile internet", false);
		        }
			}
		});
        
        register.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        	Intent intent = new Intent(getApplicationContext(),Register.class);
        	startActivity(intent);
        	}
        });
     }

     public void login(){
	     /*if(mail.getText().toString().equals("admin") && 
	        psw.getText().toString().equals("admin")){*/
	    if(true){
	        Toast.makeText(this, "Redirecting...", Toast.LENGTH_SHORT).show();
	        Intent intent = new Intent(this, MainActivity.class);
		    //intent.putExtra("ID_USR","ciao");
	        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    startActivity(intent);
		    finish();
	     }	
	     else{
	        Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
	     }
    }
}


