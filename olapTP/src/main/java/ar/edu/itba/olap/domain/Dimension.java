package olap;

import java.util.LinkedList;
import java.util.List;

public class Dimension {

	private String name;
	private List<Hierachy> hierachies;
	private List<Level> levels;
	
	
	public Dimension(String name){
		this.name = name;
		this.hierachies = new LinkedList<Hierachy>();
		this.levels = new LinkedList<Level>();
	}
	
	public void addHierachy(Hierachy h){
		hierachies.add(h);
	}
	
	public List<Hierachy> getHierachies(){
		return this.hierachies;
	}
	
	public List<Level> getLevels(){
		return this.levels;
	}
	
	public void addLevel(Level level){
		levels.add(level);
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		String string = "\n" + "DIMENSION:name"+name+"\n";
		string = string.concat("hierachiesList:" + "\n");
		for (Hierachy p : hierachies) {
			string = string.concat(p.toString() + "\n");
		}
		string = string.concat("levelsList:" + "\n");
		for (Level p : levels) {
			string = string.concat(p.toString() + "\n");
		}
		
		return string  + "\n";
	}
}
