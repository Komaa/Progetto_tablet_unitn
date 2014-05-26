package tablet_unitn.dbmanager;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tablet_unitn.treasurehunt.Map;
import android.os.AsyncTask;

public class NewMaps_db extends AsyncTask<List<Map>, Integer, List<Map>>{

	@Override
	protected List<Map> doInBackground(List<Map>... params) {
		// TODO Auto-generated method stub
		return postData(params[0]);
	}

	public List<Map> postData(List<Map> new_list) {
		String res="";
		
		HttpClient httpclient = new DefaultHttpClient();

	    // Prepare a request object
	    HttpGet httpget = new HttpGet("http://treasure-back.herokuapp.com/walking"); 

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
					Map p=new Map();
					p.SetID(obj.getString("id"));
					p.setName(obj.getString("name"));
					p.setDescription(obj.getString("description"));
					p.SetLevel(obj.getInt("level"));
					p.SetCount(obj.getInt("count"));
					new_list.add(p);
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
	    return new_list;  
	}
}