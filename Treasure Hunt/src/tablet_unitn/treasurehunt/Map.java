package tablet_unitn.treasurehunt;

public class Map {
	String name="";
	Integer trovati=0,
			totali=0;
	public Map(String name, Integer trovati, Integer totali) {
		this.name=name;
		this.trovati=trovati;
		this.totali=totali;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setTrovati(Integer trovati) {
		this.trovati=trovati;
	}
	public Integer getTrovati() {
		return trovati;
	}
	public void setTotali(Integer totali) {
		this.totali=totali;
	}
	public Integer getTotali() {
		return totali;
	}
}
