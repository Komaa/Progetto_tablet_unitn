package tablet_unitn.treasurehunt;

import java.util.Hashtable;

public class Wiki {
	Integer points = 0; //punti per risposta corretta
	
	Double lat = 0.0, //latitudine
			lng = 0.0; //longitudine
	
	String ID = "",
			name ="",
			description="",
			locationType="",
			text="";
	
	Hashtable<String, Boolean> response;
		
	// ID GOAL
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getID(){
		return ID;
	}
	//LATITUDINE DEL GOAL
	public void setLat(Double lat) {
		this.lat=lat;
	}
	public Double getLat(){
		return lat;
	}
	//LONGITUDINE DEL GOAL
	public void setLng(Double lng) {
		this.lng=lng;
	}
	public Double getLng(){
		return lng;
	}
	//Punti per risposta corretta
	public void setPoints(Integer points) {
		this.points=points;
	}
	public Integer getPoints(){
		return points;
	}
	//NOME DEL GOAL
	public void setName(String name) {
		this.name=name;
	}
	public String getName(){
		return name;
	}
	//Tipo di locazione
	public void setLocationType(String locationType) {
		this.locationType=locationType;
	}
	public String getLocationType(){
		return description;
	}
	//DESCRIZIONE DEL GOAL
	public void setDescription(String desc) {
		this.description=desc;
	}
	public String getDescription(){
		return description;
	}
	//QUESITO DEL GOAL
	public void setText(String text) {
		this.text=text;
	}
	public String getText(){
		return text;
	}
	//RISPOSTE DEL GOAL
	public void setResponse(String res, Boolean val) {
		this.response.put(res, val);
	}
	public Hashtable<String, Boolean> GetResponse(){
		return response;
	}
}
