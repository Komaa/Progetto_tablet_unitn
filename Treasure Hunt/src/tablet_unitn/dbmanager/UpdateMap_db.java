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

import android.database.CursorJoiner.Result;
import android.os.AsyncTask;

public class UpdateMap_db extends AsyncTask<String, Integer, Integer>{

//	/updatewalking/:walkingId/:username/:ntappa 	
//	controllers.ExplorerController.updateWalking(walkingId:String,username:String, ntappa:Integer)
	
	
	@Override
	protected Integer doInBackground(String... params) {
		int res = updatewalk(params[0],params[1],params[2]);
		
		return res;
	}

	public int updatewalk(String walkid, String user, String ntappa) {
		int res=0;
		HttpClient httpclient = new DefaultHttpClient();

	    // Prepare a request object
	    HttpGet httpget = new HttpGet("http://treasure-back.herokuapp.com/updatewalking/"+walkid +"/"+user+"/"+ntappa); 

	    // Execute the request
	    HttpResponse response;
	    try {
	    	response = httpclient.execute(httpget);
	        String frse = EntityUtils.toString(response.getEntity());
	        try {
				JSONObject obj = new JSONObject(frse);
				res=obj.getInt("point");
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