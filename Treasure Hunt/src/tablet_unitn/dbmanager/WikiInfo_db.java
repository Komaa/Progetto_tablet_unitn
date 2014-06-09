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

import table_unitn.utility.Utility;
import tablet_unitn.treasurehunt.Map;
import tablet_unitn.treasurehunt.Wiki;
import android.os.AsyncTask;
import android.util.Log;

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
	        Log.d("ciao12", "from wiki: "+res);
//	        try {
//	        	
//	        	String perjson="[";
//	        	String[] pairs = res.split("=");
//	       
//	        		
//	        	int[] myIntArray = new int[(pairs.length/2)+1];
//	        	for (int i=0;i<pairs.length;i++) {
//	        			if(i==0){
//	        				perjson += pairs[i].substring(1)+",";
//	        			}else if(i==(pairs.length-1)){	        				
//	        				myIntArray[i-1]=Integer.parseInt(pairs[i].substring(0, 1));	        				
//	        			}else if(i==(pairs.length-2)){
//	        				perjson += pairs[i].substring(3);
//	        				myIntArray[i-1]=Integer.parseInt(pairs[i].substring(0, 1));
//	        			}else{
//	        				perjson += pairs[i].substring(3)+",";
//	        				myIntArray[i-1]=Integer.parseInt(pairs[i].substring(0, 1));
//	        				
//	        			}
//	        	}
//	        	perjson += "]";
//	        	Log.v("per jsoooon", perjson);
//	        	Log.v("arrray contaaaa",myIntArray.toString());
//	        	
//	            JSONArray jsonArray = new JSONArray(perjson);
//	            for (int i = 0, size = jsonArray.length(); i < size; i++)
//	            {
//					JSONObject obj = jsonArray.getJSONObject(i);
//					Map p = new Map();
//					p.SetID(obj.getString("id"));
//					p.setName(obj.getString("name"));
//					p.setDescription(obj.getString("description"));
//					p.SetLevel(obj.getInt("level"));
//					p.SetCount(obj.getInt("count"));
//					p.setTappe(myIntArray[i]);
//					httpget = new HttpGet("http://treasure-back.herokuapp.com/walking/"+p.getID()); 
//					response = httpclient.execute(httpget);
//			        res = EntityUtils.toString(response.getEntity());
//			        p.setDist((Utility.calcolakm(res,0)));
//			        p.setDist_parz((Utility.calcolakm(res,myIntArray[i])));
//					continue_list.add(p);
//	            }			
//	            
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	    }catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		} catch (IOException e) {
				// TODO Auto-generated catch block
		}
	    return wiki_list;  
	}
}