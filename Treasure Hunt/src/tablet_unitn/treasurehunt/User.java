package tablet_unitn.treasurehunt;

import java.util.Hashtable;

//manca last_access e last_position

public class User {
	Integer points=0,
			logged = 0, //0 = false, 1 = true
			walkf_count = 0, //le partite completate
			currentwalk_cout = 0; //le partite correnti
	String ID="",
			mail="", 
			psw="",
			name="";
	
	Hashtable<String, Integer> mapsJoined; //String indica l'ID della mappa, l'integer indica i punti fatti.
	
			
	public User(String ID, String name, String mail, String psw, Integer points, Integer logged) {
		mapsJoined = new Hashtable<String, Integer>();
		this.ID = ID;
		this.name = name;
		this.mail = mail;
		this.psw = psw;
		this.points = points;
		this.logged = logged;
	}
	public void setID(String ID) {
		this.ID=ID;
	}
	public String getID() {
		return ID;
	}
	public void setLogged(Integer logged) {
		this.logged=logged;
	}
	public Integer getLogged() {
		return logged;
	}
	public void setPoints(Integer points) {
		this.points=points;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPartiteCorrenti(Integer walkf_count) {
		this.walkf_count=walkf_count;
	}
	public Integer getPartiteCorrenti() {
		return walkf_count;
	}
	public void setPartiteCompletate(Integer currentwalk_cout) {
		this.currentwalk_cout=currentwalk_cout;
	}
	public Integer getPartiteCompletate() {
		return currentwalk_cout;
	}
	public void setMail(String mail) {
		this.mail=mail;
	}
	public String getMail() {
		return mail;
	}
	public void setPsw(String psw) {
		this.psw=psw;
	}
	public String getPsw() {
		return psw;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setMapsJoined(Hashtable<String, Integer> mapsJoined) {
		this.mapsJoined=mapsJoined;
	}
	public Hashtable<String, Integer> getmMapsJoined() {
		return mapsJoined;
	}
}
