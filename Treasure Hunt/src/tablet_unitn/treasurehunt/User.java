package tablet_unitn.treasurehunt;

//manca last_access e last_position

public class User {
	Integer ID = 0,
			points=0,
			logged = 0; //0 = false, 1 = true
	String mail="", 
			psw="",
			name="";
			
	public User(Integer ID, String name, String mail, String psw, Integer points, Integer logged) {
		this.ID = ID;
		this.name = name;
		this.mail = mail;
		this.psw = psw;
		this.points = points;
		this.logged = logged;
	}
	public void setID(Integer ID) {
		this.ID=ID;
	}
	public Integer getID() {
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
}
