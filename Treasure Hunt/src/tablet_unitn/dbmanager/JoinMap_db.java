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
import android.util.Log;

public class JoinMap_db extends AsyncTask<String, Integer, String>{

	@Override
	protected String doInBackground(String... params) {
		return postData(params[0],params[1]);
	}

	public String postData(String mapID, String nameUSR) {
		String result="";
		
		HttpClient httpclient = new DefaultHttpClient();
Log.d("ciao1234", "mapID: "+mapID);
Log.d("ciao1234", "nameUSR: "+nameUSR);
		
	    // Prepare a request object
	    HttpGet httpget = new HttpGet("http://treasure-back.herokuapp.com/joinwalking/"+mapID+"/"+nameUSR); 
	    
	    // Execute the request
	    HttpResponse response;
	    try {
	        response = httpclient.execute(httpget);
	        String frse = EntityUtils.toString(response.getEntity());	
	        
	        try {
				JSONObject obj = new JSONObject(frse);
				
				result=obj.getString("success");
				if(result.equals("false"))
					result=obj.getString("err");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		} catch (IOException e) {
				// TODO Auto-generated catch block
		} 
	    Log.d("ciao123", "arrivo fino a qui");
	    return result;
	}

}