package tablet_unitn.treasurehunt;

public class Map {
	String mapName = "";
	
	int cpDone = 0, cpTotal = 0, //rispettivamente checkpoint raggiungi e checkpoint totali
		pathDone = 0, pathTotal = 0; //rispettivamente distanza percorsa e distanza totale
	
	String difficulty = "";
	
	//il costructor di ogni singola mappa deve contenere informazioni basilari
	//e cioè nome mappa, checkpoint totali, distanza totale e difficoltà
	public Map(String mapName, int cpTotal, int pathTotal, String difficulty) {
		this.mapName = mapName;
		this.cpTotal = cpTotal;
		this.pathTotal = pathTotal;
		this.difficulty = difficulty;
	}
	
	public String getName() {
		return mapName;
	}
	
	public String getDifficulty(){
		return this.difficulty;
	}
	
	public void setCheckpoints(int cpDone) {
		this.cpDone = cpDone;
	}
	
	public int getCheckpointsDone() {
		return this.cpDone;
	}
	
	public int getCheckpointsTotal(){
		return this.cpTotal;
	}
	
	public void setPath(int pathDone) {
		this.pathDone = pathDone;
	}
	
	public int getPathDone() {
		return this.pathDone;
	}
	
	public int getPathTotal() {
		return this.pathTotal;
	}
}
