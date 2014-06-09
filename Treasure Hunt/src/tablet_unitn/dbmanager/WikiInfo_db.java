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

import tablet_unitn.treasurehunt.Wiki;
import android.os.AsyncTask;

public class WikiInfo_db extends AsyncTask<String, Integer, List<Wiki>>{
	
	Double lat=0.0,
			lng=0.0;
	
	public WikiInfo_db(Double lat, Double lng){
		super();
		this.lat=lat;
		this.lng=lng;
	}

	@Override
	protected List<Wiki> doInBackground(String... params) {
		// TODO Auto-generated method stub
		return postData();
	}

	public List<Wiki> postData() {
		List<Wiki> wiki_list = new ArrayList<Wiki>();
		String res="";
		
		HttpClient httpclient = new DefaultHttpClient();

	    // Prepare a request object
	    HttpGet httpget = new HttpGet("http://api.wikilocation.org/articles?lat="+lat+"&lng="+lng); 

	 // Execute the request
	    HttpResponse response;
	    try {
	        response = httpclient.execute(httpget);
	        res = EntityUtils.toString(response.getEntity());
	        try {
	            JSONObject ans = new JSONObject(res);
				JSONArray arr = ans.getJSONArray("articles");
				for (int q = 0; q < arr.length(); q++) {
					JSONObject obj = arr.getJSONObject(q);
					Wiki p=new Wiki();
					p.setId(obj.getString("id"));
					p.setLat(Double.valueOf(obj.getString("lat")));
					p.setLng(Double.valueOf(obj.getString("lng")));
					p.setType(obj.getString("type"));
					p.setTitle(obj.getString("title"));
					p.setUrl_mobile(obj.getString("mobileurl"));
					p.setDistance(obj.getString("distance"));
					wiki_list.add(p);
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
	    return wiki_list;  
	}
}