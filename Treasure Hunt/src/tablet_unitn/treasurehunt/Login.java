
package tablet_unitn.treasurehunt;

import java.util.List;
import java.util.concurrent.ExecutionException;

import tablet_unitn.checkInternet.MobileInternetConnectionDetector;
import tablet_unitn.checkInternet.WIFIInternetConnectionDetector;
import tablet_unitn.dbmanager.Login_db;
import tablet_unitn.dbmanager.UserDAO_DB_impl;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	private static Context context;
	EditText mail, psw;
	Button login, register;
	
	//Internet status flag
    Boolean isMobileConnectionExist = false;
    Boolean isWifiConnectionExist = false;
    
    // Connection detector class
    MobileInternetConnectionDetector cd;
    WIFIInternetConnectionDetector wc;
    
    CheckBox checkBox;
    List<User> users;
    User user = null;
    UserDAO_DB_impl dao;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.login);
        Login.context = getApplicationContext();
        Typeface robotoThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface robotoBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        
        checkBox = (CheckBox) findViewById(R.id.remember);
        
        TextView t_name = (TextView) findViewById(R.id.t_name);
        t_name.setTypeface(robotoThin);
        TextView t_psw = (TextView) findViewById(R.id.t_psw);
        t_psw.setTypeface(robotoThin);
        TextView title_app = (TextView) findViewById(R.id.app_title);
        title_app.setTypeface(robotoBold);
        
        mail = (EditText) findViewById(R.id.login_name);
        psw = (EditText) findViewById(R.id.login_psw);
        login = (Button) findViewById(R.id.login_button);
        register = (Button) findViewById(R.id.register_button);
        
        // creating connection detector class instance
        cd = new MobileInternetConnectionDetector(getApplicationContext());
        wc = new WIFIInternetConnectionDetector(getApplicationContext());

        login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(Login.getAppContext(), "Checking..", Toast.LENGTH_SHORT).show();
				// get Internet status
		        isMobileConnectionExist = cd.checkMobileInternetConn();
		        isWifiConnectionExist = wc.checkMobileInternetConn();

		        // check for Internet status
		        if (isMobileConnectionExist||isWifiConnectionExist) {
		            // Internet Connection exists
		        	//Toast.makeText(Login.this, "Your device has mobile internet", Toast.LENGTH_SHORT).show();
		        	login();
		        } else {
		            // Internet connection doesn't exist
		        	Toast.makeText(Login.this, "No Internet Connection", Toast.LENGTH_LONG).show();
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
        
        //Controlla se c'è un utente ancora loggato
        dao = new UserDAO_DB_impl(); 
        dao.open(); 
        users = dao.getAllUser(); 
        for (int i = 0; i < users.size(); i++) {
        	if(users.get(i).getLogged()==1){
        		mail.setText(users.get(i).getMail());
        		psw.setText(users.get(i).getPsw());
        		checkBox.setChecked(true);
        		this.user=users.get(i);
        	}
    	}
     }

     public void login(){
    	String res="";
		Login_db login = new Login_db();
		try {
			res=login.execute(mail.getText().toString(), psw.getText().toString()).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	 
    	 
	    if(res.equals("true")){
	    	Toast.makeText(this, "Redirecting...", Toast.LENGTH_SHORT).show();
	    	//save or not credentials
	    	if (checkBox.isChecked()) { //se vuole salvare le credenziali
	    		if(user == null){ //se non ci sono utenti loggati o hanno fatto un login senza ricordare le credenziali
	    			int tmp=0;
	    			for (int i = 0; i < users.size(); i++) { //controllo se l'utente ha già fatto un login (già censito)
	    	        	if(users.get(i).getMail()==mail.getText().toString()){ //se ha la stessa mail è lui
	    	        		this.user=users.get(i);
	    	        		user.setLogged(1);
	    		        	dao.updateUser(user);
	    	        		tmp++;
	    	        	}
	    	    	}
	    			if(tmp==0){ //è la prima volta che fa il login
	    				user=new User(0, "noName", mail.getText().toString(), psw.getText().toString(), 0, 1);
	    				dao.insertUser(user);
	    			}
	    		}else{
	    			user.setLogged(1);
		        	dao.updateUser(user);
	    		}
	    		
	        }
	        //Toast.makeText(this, "Redirecting...", Toast.LENGTH_SHORT).show();
	        Intent intent = new Intent(this, MainActivity.class);
		    intent.putExtra("ID_USR",""+user.getID());
	        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    startActivity(intent);
		    finish();
	     }	
	     else{
	        Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
	     }
    }
     
	@Override 
	protected void onResume() { 
		dao.open(); 
		super.onResume(); 
	} 
	 
	@Override 
	protected void onPause() { 
		dao.close(); 
		super.onPause(); 
	} 
	
	public static Context getAppContext(){
 		return Login.context;
 	} 
}


