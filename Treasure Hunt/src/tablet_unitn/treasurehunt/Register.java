package tablet_unitn.treasurehunt;

import tablet_unitn.dbmanager.Sinc_dbmanager;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
	EditText name,mail,psw,rePsw;
	Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name = (EditText)findViewById(R.id.register_name);
        mail = (EditText)findViewById(R.id.register_mail);
        psw = (EditText)findViewById(R.id.register_psw);
        rePsw = (EditText)findViewById(R.id.register_rePsw);
        register = (Button)findViewById(R.id.register_button);
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
	    	register.execute(name.getText().toString(), mail.getText().toString(), psw.getText().toString());	
	    	Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
	     }	
	     else{
	        Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
	     }
    }
}