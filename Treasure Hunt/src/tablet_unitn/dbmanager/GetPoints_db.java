package tablet_unitn.dbmanager;

import java.io.IOException;
import java.util.ArrayList;
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

import tablet_unitn.treasurehunt.Goal;
import android.os.AsyncTask;
import android.util.Log;

public class GetPoints_db extends AsyncTask<List<Goal>, Integer, List<Goal>>{
	String mapID="";
	public GetPoints_db(String mapID) {
		this.mapID=mapID;
	}
	@Override
	protected List<Goal> doInBackground(List<Goal>... params) {
		// TODO Auto-generated method stub
		return postData();
	}

	public List<Goal> postData() {
		
		List<Goal> params = new ArrayList<Goal>();
		
		String res="";
		
		HttpClient httpclient = new DefaultHttpClient();

	    // Prepare a request object
	    HttpGet httpget = new HttpGet("http://treasure-back.herokuapp.com/walking/"+mapID); 
	    
	    // Execute the request
	    HttpResponse response;
	    try {
	        response = httpclient.execute(httpget);
	        res = EntityUtils.toString(response.getEntity());
	        try {
	            JSONObject un = new JSONObject(res); //prendo l'unico oggetto
	            JSONArray jsonArray = un.getJSONArray("pois"); //prendo l'array di pois
	            for (int i = 0, size = jsonArray.length(); i < size; i++)
	            {
					JSONObject obj = jsonArray.getJSONObject(i);
					Goal p=new Goal();
					p.setID(obj.getString("id"));
					p.setName(obj.getString("name"));
					p.setDescription(obj.getString("description"));
					
					String tmp=obj.getString(("coords"));
					String[] latlon = tmp.split(",");
					p.setLat(Double.parseDouble(latlon[0].substring(1)));
					p.setLng(Double.parseDouble(latlon[1].substring(0,latlon[1].length()-1)));
					if(!obj.isNull("question")){ //se ha delle domande
						JSONObject qq = obj.getJSONObject("question");
						p.setText(qq.getString("text"));
						//get all the answers
						JSONArray a = qq.getJSONArray("answers");
						for (int q = 0; q < a.length(); q++) {
							JSONObject ans = a.getJSONObject(q);
							
						    p.setResponse(ans.getString("text"), ans.getBoolean("isCorrect"));
						}
					}
					params.add(p);
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
	    return params;
	}

}