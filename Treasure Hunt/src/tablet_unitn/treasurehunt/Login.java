package tablet_unitn.treasurehunt;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	EditText mail,psw;
	Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mail = (EditText)findViewById(R.id.login_mail);
        psw = (EditText)findViewById(R.id.login_psw);
        login = (Button)findViewById(R.id.login_button);
        register = (Button)findViewById(R.id.login_register);
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

    //diego e' un veronese
    
     public void login(){
	     /*if(mail.getText().toString().equals("admin") && 
	        psw.getText().toString().equals("admin")){*/
    	 new MyAsyncLogin().execute(mail.getText().toString(), psw.getText().toString());		
 	    
	    if(true){
	        Toast.makeText(this, "Redirecting...", Toast.LENGTH_SHORT).show();
	        Intent intent = new Intent(this,MainActivity.class);
		    //intent.putExtra("ID_USR","ciao");
	        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    startActivity(intent);
		    finish();
	     }	
	     else{
	        Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
	     }
    }
     
     private class MyAsyncLogin extends AsyncTask<String, Integer, Double>{
    	 
  		@Override
  		protected Double doInBackground(String... params) {
  			// TODO Auto-generated method stub
  			postData(params[0],params[1]);
  			
  			return null;
  		}
   
  	
  
  		
  		public void postData(String name, String psw) {
  			
  			 HttpClient httpclient = new DefaultHttpClient();

  		    // Prepare a request object
  		    HttpGet httpget = new HttpGet("http://treasure-back.herokuapp.com/users/login/"+ name+"/"+mail+"/"+psw); 

  		    // Execute the request
  		    HttpResponse response;
  		    try {
  		        response = httpclient.execute(httpget);
  		       String result = EntityUtils.toString(response.getEntity());
 				Log.d("risposta", result);
//  		       try {
// 				String successo = new JSONObject(result).getString("success");
// 				if(successo.equals("true")){
// 					//Toast.makeText(getApplicationContext(), "Registration completed", Toast.LENGTH_LONG).show();
// 				}
// 			} catch (JSONException e) {
// 				// TODO Auto-generated catch block
// 				e.printStackTrace();
// 			}
  		       
  		      
  		     
  		}catch (ClientProtocolException e) {
 			// TODO Auto-generated catch block
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 			}
   
  	}
  
 }
 

}
