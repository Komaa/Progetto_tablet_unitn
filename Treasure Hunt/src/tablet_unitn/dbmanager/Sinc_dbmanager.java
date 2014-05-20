package tablet_unitn.dbmanager;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class Sinc_dbmanager extends AsyncTask<String, Integer, Double>{

	@Override
	protected Double doInBackground(String... params) {
		// TODO Auto-generated method stub
		postData(params[0],params[1],params[2]);
		
		return null;
	}

	public void postData(String name, String psw, String mail) {
		
		HttpClient httpclient = new DefaultHttpClient();

	    // Prepare a request object
	    HttpGet httpget = new HttpGet("http://treasure-back.herokuapp.com/users/register/"+ name+"/"+mail+"/"+psw); 

	    // Execute the request
	    HttpResponse response;
	    try {
	        response = httpclient.execute(httpget);
	        String result = EntityUtils.toString(response.getEntity());
	        Log.d("risposta", result);		       
	    }catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
	} catch (IOException e) {
			// TODO Auto-generated catch block
	}

	}
}