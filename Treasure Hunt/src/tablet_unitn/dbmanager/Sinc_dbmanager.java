package tablet_unitn.dbmanager;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Sinc_dbmanager{

	public String register(String name, String mail, String psw){
		
		Log.d("ciao","ciao");
		HttpResponse response = null;
		
	    try {        
	           HttpClient client = new DefaultHttpClient();
	           HttpGet request = new HttpGet();
	           request.setURI(new URI("http://treasure-back.herokuapp.com/register/" + name + "/" + psw + "/" + mail));
	           response = client.execute(request);
	       } catch (URISyntaxException e) {
	           e.printStackTrace();
	       }
	           catch (ClientProtocolException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	       } catch (IOException e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	       }
	    //Toast.makeText(this, "response is "+response ,Toast.LENGTH_LONG).show();
	    return response.toString();
	}
}
