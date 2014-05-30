package tablet_unitn.treasurehunt;

import java.util.Hashtable;

public class Goal {
	Integer lat = 0, //latitudine
			lng = 0, //longitudine
			points = 0; //punti per risposta corretta
	
	String ID = "",
			name ="",
			description="",
			locationType="",
			text="";
	
	Hashtable<String, Boolean> response;
	
	public Goal() {
		response = new Hashtable<String, Boolean>();
	}
	
	// ID GOAL
	public void SetID(String ID) {
		this.ID = ID;
	}
	public String GetID(){
		return ID;
	}
	//LATITUDINE DEL GOAL
	public void setLan(Integer lat) {
		this.lat=lat;
	}
	public Integer GetLan(){
		return lat;
	}
	//LONGITUDINE DEL GOAL
	public void setLng(Integer lng) {
		this.lng=lng;
	}
	public Integer GetLng(){
		return lng;
	}
	//Punti per risposta corretta
	public void setPoints(Integer points) {
		this.points=points;
	}
	public Integer GetPoints(){
		return points;
	}
	//NOME DEL GOAL
	public void setName(String name) {
		this.name=name;
	}
	public String GetName(){
		return name;
	}
	//Tipo di locazione
	public void setLocationType(String locationType) {
		this.locationType=locationType;
	}
	public String GetLocationType(){
		return description;
	}
	//DESCRIZIONE DEL GOAL
	public void setDescription(String desc) {
		this.description=desc;
	}
	public String GetDescription(){
		return description;
	}
	//QUESITO DEL GOAL
	public void setText(String text) {
		this.text=text;
	}
	public String GetText(){
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
