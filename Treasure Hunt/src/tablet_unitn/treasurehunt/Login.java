package tablet_unitn.treasurehunt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	EditText mail,psw;
	Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mail = (EditText)findViewById(R.id.login_mail);
        psw = (EditText)findViewById(R.id.login_psw);
        login = (Button)findViewById(R.id.login_button);
        login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				login();
			}
		});
     }

     public void login(){
	     /*if(mail.getText().toString().equals("admin") && 
	        psw.getText().toString().equals("admin")){*/
	    if(true){
	        Toast.makeText(this, "Redirecting...", Toast.LENGTH_SHORT).show();
	        Intent intent = new Intent(this,MainActivity.class);
		    //intent.putExtra("ID_USR","ciao");
	        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    startActivity(intent);
	     }	
	     else{
	        Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
	     }
    }
}
