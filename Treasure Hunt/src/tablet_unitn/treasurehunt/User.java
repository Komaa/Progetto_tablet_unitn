package tablet_unitn.treasurehunt;

public class User {
	Integer ID=0,
			points=0;
	String mail="", 
			psw="",
			name="";
	public User(String text1, String text2) {
		
	}
	public void setID(Integer ID) {
		this.ID=ID;
	}
	public Integer getID() {
		return ID;
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
