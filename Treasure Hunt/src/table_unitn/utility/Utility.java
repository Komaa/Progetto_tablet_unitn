package table_unitn.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

	public static Double calcolakm(String res, int n_tappa) throws JSONException{
		JSONObject jsonObj = new JSONObject(res);
		 Double dist=0.0;
		 Double dres=0.0;
		 
	    JSONArray jsonArray = new JSONArray(jsonObj.getString("pois"));
	    String[] mySArray = new String[(jsonArray.length()-n_tappa)];
       for (int i = (0+n_tappa), size = jsonArray.length(); i < size; i++)
       {
			JSONObject obj = jsonArray.getJSONObject(i);
			mySArray[i-n_tappa]=obj.getString(("coords"));
       }
      // Atn(-X / Sqr(-X * X + 1)) + 2 * Atn(1) 
     //arccos(cos(a1-a2)cos(b1)cos(b2)+sin(b1)sin(b2))
       
       for(int i=1;i<mySArray.length;i++){
       	String[] latlon1 = mySArray[i].split(",");
       	Double[] ll1 = { Double.parseDouble(latlon1[0].substring(1)), Double.parseDouble(latlon1[1].substring(0, latlon1[1].length()-1))};
       	String[] latlon0 = mySArray[i-1].split(",");
       	Double[] ll0 = { Double.parseDouble(latlon0[0].substring(1)), Double.parseDouble(latlon0[1].substring(0, latlon0[1].length()-1))};
       	dres=6371 * 3.1415926 * Math.sqrt( (ll0[0]-ll1[0])*(ll0[0]-ll1[0]) +Math.cos(ll0[1]/57.29578)*Math.cos(ll1[1]/57.29578) *(ll0[1]-ll1[1])*(ll0[1]-ll1[1]) )/180;
       }
   
		return dres; //il valore calcolato è corretto
	}
}
