package tablet_unitn.treasurehunt;

import java.util.ArrayList;
import java.util.List;

public class Map {
	int level = 0, //direi che qui il numero varia da 1 a 3, cioè 1=easy, 2=medium, 3=hard (AP)
		count = 0; //numero dei punti (derivabile anche da grandezza list_goal )
	
	String ID = "",
			name = "",
			description="";
	
	List<Goal> list_goal;
	
	public Map() {
		list_goal = new ArrayList<Goal>();
	}
	
//		cpDone = 0, cpTotal = 0, //rispettivamente checkpoint raggiungi e checkpoint totali
//		pathDone = 0, pathTotal = 0; //rispettivamente distanza percorsa e distanza totale
	
//	String difficulty = "";
	
	//il costructor di ogni singola mappa deve contenere informazioni basilari
	//e cioè nome mappa, checkpoint totali, distanza totale e difficoltà
	/*public Map(String mapName, int cpTotal, int pathTotal, String difficulty) {
		this.mapName = mapName;
		this.cpTotal = cpTotal;
		this.pathTotal = pathTotal;
		this.difficulty = difficulty;
	}*/
	
	//ID map
	public void SetID(String ID){
		this.ID=ID;
	}
	public String getID() {
		return ID;
	}
	//livello map
	public void SetLevel(Integer level){
		this.level=level;
	}
	public Integer getLevel() {
		return level;
	}
	//numero punti map
	public void SetCount(Integer count){
		this.count=count;
	}
	public Integer getCount() {
		return count;
	}
	//nome mappa
	public void setName(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
	//descrizione mappa
	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription() {
		return description;
	}
	//lista punti mappa
	public void setListGoal(List<Goal> list_goal){
		this.list_goal=list_goal;
	}
	public List<Goal> getListGoal() {
		return list_goal;
	}
	
	
//	public String getDifficulty(){
//		return this.difficulty;
//	}
//	
//	public void setCheckpoints(int cpDone) {
//		this.cpDone = cpDone;
//	}
//	
//	public int getCheckpointsDone() {
//		return this.cpDone;
//	}
//	
//	public int getCheckpointsTotal(){
//		return this.cpTotal;
//	}
//	
//	public void setPath(int pathDone) {
//		this.pathDone = pathDone;
//	}
//	
//	public int getPathDone() {
//		return this.pathDone;
//	}
//	
//	public int getPathTotal() {
//		return this.pathTotal;
//	}
}
