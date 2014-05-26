package tablet_unitn.treasurehunt;

import java.util.Hashtable;

public class Goal {
	Integer ID = 0,
			lat = 0, //latitudine
			lng = 0; //longitudine
	
	String name ="",
			description="",
			question="";
	
	Hashtable<String, Boolean> response;
	
	public Goal() {
		response = new Hashtable<String, Boolean>();
	}
	
	// ID GOAL
	public void SetID(Integer ID) {
		this.ID = ID;
	}
	public Integer GetID(){
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
	//NOME DEL GOAL
	public void setName(String name) {
		this.name=name;
	}
	public String GetName(){
		return name;
	}
	//DESCRIZIONE DEL GOAL
	public void setDescription(String desc) {
		this.description=desc;
	}
	public String GetDescription(){
		return description;
	}
	//QUESITO DEL GOAL
	public void setQuestion(String question) {
		this.question=question;
	}
	public String GetQuestion(){
		return question;
	}
	//RISPOSTE DEL GOAL
	public void setResponse(String res, Boolean val) {
		this.response.put(res, val);
	}
	public Hashtable<String, Boolean> GetResponse(){
		return response;
	}
}
