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

import tablet_unitn.treasurehunt.User;
import android.os.AsyncTask;
import android.util.Log;

public class GetUserInfo_db extends AsyncTask<User, Integer, User>{

	@Override
	protected User doInBackground(User... params) {
		return postData(params[0]);
	}

	public User postData(User user) {
		
		String res="";
		HttpClient httpclient = new DefaultHttpClient();

	    // Prepare a request object
	    HttpGet httpget = new HttpGet("http://treasure-back.herokuapp.com/users/datiu/"+user.getName()); 

	    // Execute the request
	    HttpResponse response;
	    try {
	        response = httpclient.execute(httpget);
	        res = EntityUtils.toString(response.getEntity());
	        Log.d("res", "res: "+res);
	        try {
	            JSONObject obj = new JSONObject(res);
	        	user.setID(obj.getString("id"));
	        	user.setPoints(obj.getInt("punti"));
				user.setPartiteCorrenti(obj.getInt("currentwalk_cout"));
				user.setPartiteCompletate(obj.getInt("walkf_count"));
	            	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		} catch (IOException e) {
				// TODO Auto-generated catch block
		}
	    return user;  
	}
}