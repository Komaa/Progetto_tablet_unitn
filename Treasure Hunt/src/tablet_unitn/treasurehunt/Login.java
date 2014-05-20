
package tablet_unitn.treasurehunt;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.login);
        
        Typeface robotoThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface robotoBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        
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
				login();
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


