package tablet_unitn.dbmanager;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;

public class Register_db extends AsyncTask<String, Integer, String>{

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String res=postData(params[0],params[1],params[2]);
		
		return res;
	}

	public String postData(String name, String psw, String mail) {
		String result="";
		HttpClient httpclient = new DefaultHttpClient();

	    // Prepare a request object
	    HttpGet httpget = new HttpGet("http://treasure-back.herokuapp.com/users/register/"+ name+"/"+mail+"/"+psw); 

	    // Execute the request
	    HttpResponse response;
	    try {
	        response = httpclient.execute(httpget);
	        result = EntityUtils.toString(response.getEntity());	 
	        try {
				JSONObject obj = new JSONObject(result);
				result=obj.getString("success");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
	} catch (IOException e) {
			// TODO Auto-generated catch block
	}
	return result;  
	}
}