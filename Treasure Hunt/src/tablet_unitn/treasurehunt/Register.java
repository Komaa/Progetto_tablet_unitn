package tablet_unitn.treasurehunt;

import java.util.concurrent.ExecutionException;

import tablet_unitn.dbmanager.Sinc_dbmanager;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {
	EditText name,mail,psw,rePsw;
	Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.register);
        
        Typeface robotoThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface robotoBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        
        name = (EditText)findViewById(R.id.register_name);
        mail = (EditText)findViewById(R.id.register_mail);
        psw = (EditText)findViewById(R.id.register_psw);
        rePsw = (EditText)findViewById(R.id.register_rePsw);
        register = (Button)findViewById(R.id.register_button);
        
        TextView t_mail = (TextView) findViewById(R.id.t_mail);
        TextView t_name = (TextView) findViewById(R.id.t_name);
        TextView t_psw = (TextView) findViewById(R.id.t_psw);
        TextView t_rePsw = (TextView) findViewById(R.id.t_rePsw);
        TextView title_app = (TextView) findViewById(R.id.app_title);
        
        title_app.setTypeface(robotoBold);
        t_mail.setTypeface(robotoThin); t_name.setTypeface(robotoThin);
        t_psw.setTypeface(robotoThin); t_rePsw.setTypeface(robotoThin);
        
        register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				register();
			}
		});
     }

     public void register(){
	    if(!name.getText().toString().equals("") && !mail.getText().toString().equals("") && !psw.getText().toString().equals("") && psw.getText().toString().equals(rePsw.getText().toString())){
	    	String res = "ciao";
	    	Sinc_dbmanager register = new Sinc_dbmanager();
	    	try {
				res=register.execute(name.getText().toString(), mail.getText().toString(), psw.getText().toString()).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    	if(res.equals("true"))
	    	Toast.makeText(this, "Registration completed", Toast.LENGTH_SHORT).show();
	    	else
	    	Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
	     }	
	     else{
	        Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
	     }
    }
}