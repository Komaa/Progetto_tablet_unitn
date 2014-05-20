package tablet_unitn.treasurehunt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import tablet_unitn.dbmanager.Sinc_dbmanager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
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
    	Sinc_dbmanager reg = new Sinc_dbmanager();
	    //if(name.getText().toString() != "" && mail.getText().toString() != "" && psw.getText().toString() != "" && psw.getText().toString() == rePsw.getText().toString()){
	    if(true){    
	    	new MyAsyncTask().execute(name.getText().toString(), mail.getText().toString(), psw.getText().toString());		
	    	//reg.register(name.getText().toString(), mail.getText().toString(), psw.getText().toString());
	     }	
	     else{
	        Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
	     }
    }
     
     
     
     private class MyAsyncTask extends AsyncTask<String, Integer, Double>{
    	 
 		@Override
 		protected Double doInBackground(String... params) {
 			// TODO Auto-generated method stub
 			postData(params[0],params[1],params[2]);
 			
 			return null;
 		}
  
 	
 
 		
 		public void postData(String name, String psw, String mail) {
 			// Create a new HttpClient and Post Header
// 			HttpClient httpclient = new DefaultHttpClient();
// 			HttpPost httppost = new HttpPost("http://treasure-back.herokuapp.com/users/register/");
//  
// 			try {
// 				// Add your data
// 				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
// 				nameValuePairs.add(new BasicNameValuePair("username", name));
// 				nameValuePairs.add(new BasicNameValuePair("password", psw));
// 				nameValuePairs.add(new BasicNameValuePair("mail", mail));
// 				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
// 				Log.d("controllo", httppost.toString());
// 				// Execute HTTP Post Request
// 				HttpResponse response = httpclient.execute(httppost);
// 
// 				String result = EntityUtils.toString(response.getEntity());
// 				Log.d("risposta", result);
// 			} catch (ClientProtocolException e) {
// 				// TODO Auto-generated catch block
// 			} catch (IOException e) {
// 				// TODO Auto-generated catch block
// 			}
 			
 			
 			 HttpClient httpclient = new DefaultHttpClient();

 		    // Prepare a request object
 		    HttpGet httpget = new HttpGet("http://treasure-back.herokuapp.com/users/register/"+ name+"/"+mail+"/"+psw); 

 		    // Execute the request
 		    HttpResponse response;
 		    try {
 		        response = httpclient.execute(httpget);
 		       String result = EntityUtils.toString(response.getEntity());
				Log.d("risposta", result);
// 		       try {
//				String successo = new JSONObject(result).getString("success");
//				if(successo.equals("true")){
//					//Toast.makeText(getApplicationContext(), "Registration completed", Toast.LENGTH_LONG).show();
//				}
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
 		       
 		      
 		     
 		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
  
 	}
 
}
}
