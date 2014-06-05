package tablet_unitn.dbmanager;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tablet_unitn.treasurehunt.User;
import android.os.AsyncTask;

public class GetUserInfo_db extends AsyncTask<User, Integer, User>{

	@Override
	protected User doInBackground(User... params) {
		return postData(params[0]);
	}

	public User postData(User user) {
		
		String res="";
		HttpClient httpclient = new DefaultHttpClient();

	    // Prepare a request object
	    HttpGet httpget = new HttpGet("http://treasure-back.herokuapp.com/users/datiu/"+user.getMail()); 

	    // Execute the request
	    HttpResponse response;
	    try {
	        response = httpclient.execute(httpget);
	        res = EntityUtils.toString(response.getEntity());
	        try {
	            JSONArray jsonArray = new JSONArray(res);
	            for (int i = 0, size = jsonArray.length(); i < size; i++)
	            {
					JSONObject obj = jsonArray.getJSONObject(i);
					user.setID(obj.getString("id"));
					user.setName(obj.getString("username"));
					user.setPoints(obj.getInt("description"));
					user.setPartiteCorrenti(obj.getInt("walkf_count"));
					user.setPartiteCompletate(obj.getInt("currentwalk_cout"));
	            }			
	            
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