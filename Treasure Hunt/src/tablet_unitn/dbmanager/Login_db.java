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

public class Login_db extends AsyncTask<String, Integer, String[]>{

	@Override
	protected String[] doInBackground(String... params) {
		// TODO Auto-generated method stub
		String[] res = postData(params[0],params[1]);
		
		return res;
	}

	public String[] postData(String name, String psw) {
		String[] res=new String[3];
		HttpClient httpclient = new DefaultHttpClient();

	    // Prepare a request object
	    HttpGet httpget = new HttpGet("http://treasure-back.herokuapp.com/users/login/"+ name+"/"+psw); 

	    // Execute the request
	    HttpResponse response;
	    try {
	    	response = httpclient.execute(httpget);
	        String frse = EntityUtils.toString(response.getEntity());
	        try {
				JSONObject obj = new JSONObject(frse);
				res[1]=obj.getString("success");
				if(res[1].equals("false"))
					res[2]=obj.getString("err");
				else
					res[0]=obj.getString("token");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
	} catch (IOException e) {
			// TODO Auto-generated catch block
	}
	return res;  
	}
}